package ca.levio.taskscheduler.service;

import ca.levio.commonbean.dto.InterviewDto;
import ca.levio.commonbean.messageevent.NewIterationOfSelectionMessageEvent;
import ca.levio.taskscheduler.mapper.InterviewDtoNewIterationOfSelectionMessageEventMapper;
import ca.levio.taskscheduler.model.TechnicalAdvisorSelectionProcess;
import ca.levio.taskscheduler.repository.TechnicalAdvisorSelectionProcessRepository;
import ca.levio.messagequeue.producer.MessageQueueProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TechnicalAdvisorSelectionProcessService {

    private final TechnicalAdvisorSelectionProcessRepository technicalAdvisorSelectionProcessRepository;
    private final InterviewDtoNewIterationOfSelectionMessageEventMapper interviewDtoNewIterationOfSelectionMessageEventMapper;
    private final MessageQueueProducer messageQueueProducer;
    private final TaskScheduler taskScheduler;
    private final InterviewService interviewService;

    public void createTaskForNextIteration(TechnicalAdvisorSelectionProcess technicalAdvisorSelectionProcess) {
        createTechnicalAdvisorSelectionProcess(technicalAdvisorSelectionProcess);
        scheduleTechnicalAdvisorSelectionTask(technicalAdvisorSelectionProcess);
    }

    public TechnicalAdvisorSelectionProcess createTechnicalAdvisorSelectionProcess(TechnicalAdvisorSelectionProcess technicalAdvisorSelectionProcess) {
        technicalAdvisorSelectionProcess.setLastDateProcess(LocalDateTime.now());
        return technicalAdvisorSelectionProcessRepository.save(technicalAdvisorSelectionProcess);
    }

    public void scheduleTechnicalAdvisorSelectionTask(TechnicalAdvisorSelectionProcess technicalAdvisorSelectionProcess) {
        LocalDateTime nextDateProcess = selectNextRunProcessTime(technicalAdvisorSelectionProcess);
        if (nextDateProcess != null) {
            System.out.println("create schedule task for interview : " + technicalAdvisorSelectionProcess.getInterview());
            Duration duration = Duration.between(LocalDateTime.now(), nextDateProcess);
            //taskScheduler.scheduleAtFixedRate(createRunnable(technicalAdvisorSelectionProcess), duration);
        }
    }
 
    private Runnable createRunnable(TechnicalAdvisorSelectionProcess technicalAdvisorSelectionProcess) {
        return () -> newIterationInterview(technicalAdvisorSelectionProcess.getInterview());
    }

    public void newIterationInterview(String interview) {
        log.info("new iteration interivew service {}", interview);
        Optional<TechnicalAdvisorSelectionProcess> optionalLastSelectionProcess = technicalAdvisorSelectionProcessRepository.findByInterview(interview);
        if (optionalLastSelectionProcess.isPresent()) {
            TechnicalAdvisorSelectionProcess technicalAdvisorSelectionProcess = optionalLastSelectionProcess.get();
            updateTechnicalAdvisorSelectionProcess(technicalAdvisorSelectionProcess);
            sendNewIterationOfSelectionMessageEvent(technicalAdvisorSelectionProcess);
        }
    }

    public Collection<TechnicalAdvisorSelectionProcess> selectAllTechnicalAdvisorSelectionProcess() {
        return technicalAdvisorSelectionProcessRepository.findAll();
    }

    public void deleteTechnicalAdvisorSelectionProcess(String interview) {
        Optional<TechnicalAdvisorSelectionProcess> optionalLastSelectionProcess = technicalAdvisorSelectionProcessRepository.findByInterview(interview);
        if (optionalLastSelectionProcess.isPresent()) {
            technicalAdvisorSelectionProcessRepository.delete(optionalLastSelectionProcess.get());
        }
    }

    public void updateTechnicalAdvisorSelectionProcess(TechnicalAdvisorSelectionProcess technicalAdvisorSelectionProcess) {
        technicalAdvisorSelectionProcess.setLastDateProcess(LocalDateTime.now());
        technicalAdvisorSelectionProcessRepository.save(technicalAdvisorSelectionProcess);
    }

    public LocalDateTime selectNextRunProcessTime(TechnicalAdvisorSelectionProcess technicalAdvisorSelectionProcess) {
        return technicalAdvisorSelectionProcess.getLastDateProcess().plusMinutes(technicalAdvisorSelectionProcess.getDelayBeforeRetrying());
    }

    public void sendNewIterationOfSelectionMessageEvent(TechnicalAdvisorSelectionProcess technicalAdvisorSelectionProcess) {
        InterviewDto interviewDto = interviewService.getInterview(technicalAdvisorSelectionProcess.getInterview());
        NewIterationOfSelectionMessageEvent newIterationOfSelectionMessageEvent = interviewDtoNewIterationOfSelectionMessageEventMapper.interviewDtoToNewIterationOfSelectionMessageEvent(interviewDto);
        messageQueueProducer.send(newIterationOfSelectionMessageEvent);
    }
}
