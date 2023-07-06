package ca.levio.interviewrequest.service;

import ca.levio.commonbean.dto.EligibleTechnicalAdvisorDto;
import ca.levio.commonbean.messageevent.InterviewAcceptedMessageEvent;
import ca.levio.commonbean.messageevent.InterviewAlreadyAcceptedMessageEvent;
import ca.levio.commonbean.messageevent.InterviewAssignedMessageEvent;
import ca.levio.commonbean.messageevent.InterviewDeclinedMessageEvent;
import ca.levio.commonbean.messageevent.NewInterviewRequestMessageEvent;
import ca.levio.interviewrequest.enums.StatusOfRequest;
import ca.levio.interviewrequest.model.InterviewRequest;
import ca.levio.interviewrequest.repository.InterviewRequestRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import ca.levio.messagequeue.producer.MessageQueueProducer;

import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class InterviewRequestService {

    private final InterviewRequestRepository interviewRequestRepository;
    private final TechnicalAdvisorService technicalAdvisorService;
    private final MessageQueueProducer messageQueueProducer;

    public void createInterviewRequest(InterviewRequest interviewRequest, Integer numberOfTechnicalAdvisorByBatch, String jobPosition, String levelOfExpertise) {
        log.info("create interview request service");
        List<EligibleTechnicalAdvisorDto> eligibleTechnicalAdvisorDtos = technicalAdvisorService.selectEligibleTechnicalAdvisors(jobPosition, levelOfExpertise, interviewRequest.getInterview(), numberOfTechnicalAdvisorByBatch);

        if (eligibleTechnicalAdvisorDtos != null) {
            eligibleTechnicalAdvisorDtos.forEach(eligibleTechnicalAdvisorDto -> {
                InterviewRequest interviewRequestTemp = InterviewRequest.builder()
                        .interview(interviewRequest.getInterview())
                        .recruiterEmail(interviewRequest.getRecruiterEmail())
                        .recruiterName(interviewRequest.getRecruiterName())
                        .technicalAdvisor(eligibleTechnicalAdvisorDto.getId())
                        .technicalAdvisorEmail(eligibleTechnicalAdvisorDto.getEmail())
                        .technicalAdvisorName(eligibleTechnicalAdvisorDto.getName())
                        .build();
                createInterviewRequest(interviewRequestTemp);
            });
        } else {
            log.info("Aucun Technical Advisor trouvé.");
        }
    }

    protected void createInterviewRequest(InterviewRequest interviewRequest) {
        interviewRequest = interviewRequestRepository.saveAndFlush(interviewRequest);
        sendNewInterviewRequestMessageEvent(interviewRequest);
    }

    public void sendNewInterviewRequestMessageEvent(InterviewRequest interviewRequest) {
        NewInterviewRequestMessageEvent newInterviewRequestMessageEvent = new NewInterviewRequestMessageEvent(interviewRequest.getTechnicalAdvisorEmail(), interviewRequest.getTechnicalAdvisorName(), interviewRequest.getInterview(), interviewRequest.getId());
        messageQueueProducer.send(newInterviewRequestMessageEvent);
    }

    public void sendInterviewAcceptedMessageEvent(InterviewRequest interviewRequest) {
        InterviewAcceptedMessageEvent interviewAcceptedMessageEvent = new InterviewAcceptedMessageEvent(interviewRequest.getTechnicalAdvisorEmail(), interviewRequest.getTechnicalAdvisorName(), interviewRequest.getInterview(), interviewRequest.getRecruiter());
        messageQueueProducer.send(interviewAcceptedMessageEvent);
    }

    public void sendInterviewAlreadyAcceptedMessageEvent(InterviewRequest interviewRequest) {
        InterviewAlreadyAcceptedMessageEvent interviewAlreadyAcceptedMessageEvent = new InterviewAlreadyAcceptedMessageEvent(interviewRequest.getTechnicalAdvisorEmail(), interviewRequest.getTechnicalAdvisorName(), interviewRequest.getInterview());
        messageQueueProducer.send(interviewAlreadyAcceptedMessageEvent);
    }

    public void sendInterviewAssignedMessageEvent(InterviewRequest interviewRequest) {
        InterviewAssignedMessageEvent interviewAssignedMessageEvent = new InterviewAssignedMessageEvent(interviewRequest.getRecruiterEmail(), interviewRequest.getRecruiterName(), interviewRequest.getInterview(), interviewRequest.getTechnicalAdvisor());
        messageQueueProducer.send(interviewAssignedMessageEvent);
    }

    public void sendInterviewDeclinedMessageEvent(InterviewRequest interviewRequest) {
        InterviewDeclinedMessageEvent interviewDeclinedMessageEvent = new InterviewDeclinedMessageEvent(interviewRequest.getTechnicalAdvisorEmail(), interviewRequest.getTechnicalAdvisorName(), interviewRequest.getInterview());
        messageQueueProducer.send(interviewDeclinedMessageEvent);
    }

    public List<InterviewRequest> getInterviewRequests() {
        log.info("get all interivew requests service");
        return interviewRequestRepository.findAll();
    }

    public void acceptInterviewRequest(String id) {
        log.info("accept interivew request service");
        InterviewRequest interviewRequest = getInterviewRequestById(id);
        if (interviewRequest != null) {
            if (interviewAlreadyAccepted(interviewRequest.getInterview())) {
                interviewRequest.setAcceptedAt(LocalDateTime.now());
                updateInterviewRequestStatus(interviewRequest, StatusOfRequest.ACCEPTED);
                sendInterviewAlreadyAcceptedMessageEvent(interviewRequest);
            } else {
                interviewRequest.setAcceptedAt(LocalDateTime.now());
                updateInterviewRequestStatus(interviewRequest, StatusOfRequest.ASSIGNED);
                sendInterviewAcceptedMessageEvent(interviewRequest);
                sendInterviewAssignedMessageEvent(interviewRequest);
            }
        }
    }

    public boolean interviewAlreadyAccepted(String interview) {
        // TODO : use UUID
        return interviewRequestRepository.existsByInterviewAndStatusOfRequestIn(interview, Arrays.asList(StatusOfRequest.ACCEPTED, StatusOfRequest.ASSIGNED));
    }

    public void rejectInterviewRequest(String id) {
        log.info("reject interivew request service");
        InterviewRequest interviewRequest = getInterviewRequestById(id);
        if (interviewRequest != null) {
            StatusOfRequest oldStatusOfRequest = interviewRequest.getStatusOfRequest();
            updateInterviewRequestStatus(interviewRequest, StatusOfRequest.REJECTED_BY_TECHNICAL_ADVISOR);
            if (oldStatusOfRequest.equals(StatusOfRequest.ASSIGNED)) {
                sendNotificationToNextTechnicalAdvisor(interviewRequest.getInterview());
            }
        }
    }

    public void sendNotificationToNextTechnicalAdvisor(String interview) {
        Optional<InterviewRequest> optionalInterviewRequest = interviewRequestRepository.findFirstByInterviewAndStatusOfRequestOrderByAcceptedAtAsc(interview, StatusOfRequest.ACCEPTED);
        if (optionalInterviewRequest.isPresent()) {
            InterviewRequest interviewRequest = optionalInterviewRequest.get();
            updateInterviewRequestStatus(interviewRequest, StatusOfRequest.ASSIGNED);
            sendInterviewAcceptedMessageEvent(interviewRequest);
            sendInterviewAssignedMessageEvent(interviewRequest);
        }
    }

    public void declineInterviewRequest(String id) {
        log.info("decline interivew request service");
        InterviewRequest interviewRequest = getInterviewRequestById(id);
        if (interviewRequest != null) {
            updateInterviewRequestStatus(interviewRequest, StatusOfRequest.DECLINED_BY_RECRUITER);
            sendInterviewDeclinedMessageEvent(interviewRequest);
        }
    }

    public void updateInterviewRequestStatus(InterviewRequest interviewRequest, StatusOfRequest statusOfRequest) {
        interviewRequest.setStatusOfRequest(statusOfRequest);
        interviewRequestRepository.save(interviewRequest);
    }

    public InterviewRequest getInterviewRequestById(String id) {
        Optional<InterviewRequest> optionalInterviewRequest = interviewRequestRepository.findById(id);
        if (optionalInterviewRequest.isPresent()) {
            return optionalInterviewRequest.get();
        }
        return null;
    }
}
