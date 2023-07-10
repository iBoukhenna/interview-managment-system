package ca.levio.taskscheduler.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ca.levio.commonbean.messageevent.NewInterviewMessageEvent;
import ca.levio.taskscheduler.service.TechnicalAdvisorSelectionProcessService;
import ca.levio.taskscheduler.mapper.TechnicalAdvisorSelectionProcessNewInterviewMessageEventMapper;
import ca.levio.taskscheduler.model.TechnicalAdvisorSelectionProcess;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class NewInterviewKafkaMessageQueueConsumer {

    private final TechnicalAdvisorSelectionProcessService technicalAdvisorSelectionProcessService;
    private final TechnicalAdvisorSelectionProcessNewInterviewMessageEventMapper interviewRequestNewInterviewMessageEventMapper;

    @KafkaListener(topics = NewInterviewMessageEvent.TOPIC)
    public void receive(NewInterviewMessageEvent newInterviewMessageEvent) {
        log.info("receive interivew data from interview {}", newInterviewMessageEvent);
        TechnicalAdvisorSelectionProcess technicalAdvisorSelectionProcess = interviewRequestNewInterviewMessageEventMapper.NewInterviewMessageEventToTechnicalAdvisorSelectionProcess(newInterviewMessageEvent);
        technicalAdvisorSelectionProcessService.createTaskForNextIteration(technicalAdvisorSelectionProcess);
    }
}
