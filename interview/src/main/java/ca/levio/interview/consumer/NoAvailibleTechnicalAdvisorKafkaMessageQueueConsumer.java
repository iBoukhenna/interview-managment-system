package ca.levio.interview.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ca.levio.interview.service.InterviewService;
import ca.levio.messagequeue.messageevent.NoAvailibleTechnicalAdvisorMessageEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class NoAvailibleTechnicalAdvisorKafkaMessageQueueConsumer {

    private InterviewService interviewService;

    @KafkaListener(topics = NoAvailibleTechnicalAdvisorMessageEvent.TOPIC)
    public void receive(NoAvailibleTechnicalAdvisorMessageEvent noAvailibleTechnicalAdvisorMessageEvent) {
        log.info("send notification {}", noAvailibleTechnicalAdvisorMessageEvent);
        interviewService.updateInterviewNoAvailibleTechnicalAdvisor(noAvailibleTechnicalAdvisorMessageEvent.getInterview());
    }
}
