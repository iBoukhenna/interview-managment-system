package ca.levio.taskscheduler.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ca.levio.commonbean.messageevent.AllInterviewRequestRejectedMessageEvent;
import ca.levio.taskscheduler.service.TechnicalAdvisorSelectionProcessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class AllInterviewRequestRejectedKafkaMessageQueueConsumer {

    private final TechnicalAdvisorSelectionProcessService technicalAdvisorSelectionProcessService;

    @KafkaListener(topics = AllInterviewRequestRejectedMessageEvent.TOPIC)
    public void receive(AllInterviewRequestRejectedMessageEvent allInterviewRequestRejectedMessageEvent) {
        log.info("receive interivew data {}", allInterviewRequestRejectedMessageEvent);
        technicalAdvisorSelectionProcessService.newIterationInterview(allInterviewRequestRejectedMessageEvent.getInterview());
    }
}
