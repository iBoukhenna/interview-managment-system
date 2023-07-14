package ca.levio.interviewrequest.service;

import ca.levio.interviewrequest.dto.EligibleTechnicalAdvisorDto;
import ca.levio.interviewrequest.dto.InterviewDto;
import ca.levio.interviewrequest.enums.StatusOfRequest;
import ca.levio.interviewrequest.model.InterviewRequest;
import ca.levio.interviewrequest.model.TechnicalAdvisorSelectionProcess;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class InterviewRequestProcessService {

    private final MessageQueueProducerService messageQueueProducerService;
    private final TechnicalAdvisorService technicalAdvisorService;
    private final InterviewService interviewService;
    private final TechnicalAdvisorSelectionProcessService technicalAdvisorSelectionProcessService;
    private final InterviewRequestService interviewRequestService;
    private final TaskScheduler taskScheduler;
    
    public void acceptInterviewRequestProcess(String id) {
        log.info("accept interview request process");
        InterviewRequest interviewRequest = interviewRequestService.getInterviewRequestById(id);
        if (interviewRequest == null) return;

        if (interviewRequestService.checkIfInterviewIsAlreadyAccepted(interviewRequest.getInterview())) {
            interviewRequestService.accepteInterviewRequest(interviewRequest);
        } else {
            interviewRequestService.assigneInterviewRequest(interviewRequest);
        }
    }

    public void rejectInterviewRequestProcess(String id) {
        log.info("reject interview request process : " + id);
        InterviewRequest interviewRequest = interviewRequestService.getInterviewRequestById(id);
        if (interviewRequest == null) return;

        StatusOfRequest oldStatusOfRequest = interviewRequest.getStatusOfRequest();
        interviewRequestService.rejectInterviewRequest(interviewRequest);
        if (interviewRequestService.checkIfAllInterviewRequestRejected(interviewRequest)) {
            runNewIterationOfProcess(interviewRequest.getInterview());
        } else if (oldStatusOfRequest.equals(StatusOfRequest.ASSIGNED)) {
            sendNotificationToNextTechnicalAdvisor(interviewRequest.getInterview());
        }
    }

    public void sendNotificationToNextTechnicalAdvisor(String interview) {
        log.info("send to next technical advisor interview : " + interview);
        InterviewRequest interviewRequest = interviewRequestService.getInterviewRequestAccepetedByInterview(interview);
        if (interviewRequest == null)  return;

        interviewRequestService.assigneInterviewRequest(interviewRequest);
    }

    public void declineInterviewRequestProcess(String id) {
        log.info("decline interview request process : " + id);
        InterviewRequest interviewRequest = interviewRequestService.getInterviewRequestById(id);
        if (interviewRequest == null) return;

        interviewRequestService.declineInterviewRequest(interviewRequest);
    }

    public void runNewIterationOfProcess(String interview) {
        log.info("run new iteration process interview : " + interview);
        InterviewDto interviewDto = interviewService.getInterview(interview);
        if (interviewDto.getStateOfInterview().equals("OPEN")) {
            createInterviewRequestsProcess(interviewDto);
        } else {
            technicalAdvisorSelectionProcessService.deleteTechnicalAdvisorSelectionProcess(interview);
        }
    }

    public void createInterviewRequestsProcess(InterviewDto interviewDto) {
        log.info("create interview requests process interview : " + interviewDto.getInterview());
        TechnicalAdvisorSelectionProcess technicalAdvisorSelectionProcess = technicalAdvisorSelectionProcessService.getTechnicalAdvisorSelectionProcessByInterviewDto(interviewDto);
        createSchedulerTaskForTechnicalAdvisorSelectionProcess(technicalAdvisorSelectionProcess);

        List<EligibleTechnicalAdvisorDto> eligibleTechnicalAdvisorDtos = technicalAdvisorService.selectEligibleTechnicalAdvisors(interviewDto.getJobPosition(), interviewDto.getLevelOfExpertise(), interviewDto.getInterview(), interviewDto.getNumberOfTechnicalAdvisorByBatch());
        if (eligibleTechnicalAdvisorDtos != null) {
            eligibleTechnicalAdvisorDtos.forEach(eligibleTechnicalAdvisorDto -> {
                log.info(eligibleTechnicalAdvisorDto.getId());
                InterviewRequest interviewRequest = InterviewRequest.builder()
                        .interview(interviewDto.getInterview())
                        .recruiter(interviewDto.getRecruiter())
                        .technicalAdvisor(eligibleTechnicalAdvisorDto.getId())
                        .build();
                createInterviewRequest(interviewRequest);
            });
        } else {
            noAvailibleTechnicalAdvisor(interviewDto);
        }
    }

    protected void noAvailibleTechnicalAdvisor(InterviewDto interviewDto) {
        technicalAdvisorSelectionProcessService.deleteTechnicalAdvisorSelectionProcess(interviewDto.getInterview());
        messageQueueProducerService.sendNoAvailibleTechnicalAdvisorMessageEvent(interviewDto);
    }

    protected void createInterviewRequest(InterviewRequest interviewRequest) {
        log.info("create interview request interview/technical advisor : " + interviewRequest.getInterview() + "/" + interviewRequest.getTechnicalAdvisor());
        interviewRequest = interviewRequestService.createInterviewRequest(interviewRequest);
        messageQueueProducerService.sendNewInterviewRequestMessageEvent(interviewRequest);
    }

    public void createSchedulerTaskForAllTechnicalAdvisorSelectionProcess() {
        log.info("create SchedulerTask for all TechnicalAdvisorSelectionProcess");
        Collection<TechnicalAdvisorSelectionProcess> technicalAdvisorSelectionProcessList = technicalAdvisorSelectionProcessService.selectAllTechnicalAdvisorSelectionProcess();
		technicalAdvisorSelectionProcessList.forEach(technicalAdvisorSelectionProcess -> createSchedulerTaskForTechnicalAdvisorSelectionProcess(technicalAdvisorSelectionProcess));
    }

    public void createSchedulerTaskForTechnicalAdvisorSelectionProcess(TechnicalAdvisorSelectionProcess technicalAdvisorSelectionProcess) {
        log.info("create SchedulerTask for TechnicalAdvisorSelectionProcess interview : " + technicalAdvisorSelectionProcess.getInterview());
        LocalDateTime nextDateProcess = technicalAdvisorSelectionProcessService.selectNextRunProcessTime(technicalAdvisorSelectionProcess);
        if (nextDateProcess != null) {
            Duration duration = Duration.between(LocalDateTime.now(), nextDateProcess);
            Instant instant = nextDateProcess.atZone(ZoneId.systemDefault()).toInstant();
            taskScheduler.scheduleWithFixedDelay(createRunnable(technicalAdvisorSelectionProcess.getInterview()), instant, duration);
        }
    }

    private Runnable createRunnable(String interview) {
        return () -> runNewIterationOfProcess(interview);
    }
}
