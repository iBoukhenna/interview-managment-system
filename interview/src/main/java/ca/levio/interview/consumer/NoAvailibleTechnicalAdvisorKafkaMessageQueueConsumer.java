package ca.levio.interview.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import ca.levio.interview.service.InterviewService;
import ca.levio.interview.common.JsonConverter;
import ca.levio.interview.messageevent.NoAvailibleTechnicalAdvisorMessageEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class NoAvailibleTechnicalAdvisorKafkaMessageQueueConsumer {

    private InterviewService interviewService;

    @KafkaListener(topics = NoAvailibleTechnicalAdvisorMessageEvent.TOPIC)
    public void receive(String json) {
        try {
            log.info("send notification {}", json);
            NoAvailibleTechnicalAdvisorMessageEvent noAvailibleTechnicalAdvisorMessageEvent = JsonConverter.fromJson(json, NoAvailibleTechnicalAdvisorMessageEvent.class);
            interviewService.updateInterviewNoAvailibleTechnicalAdvisor(noAvailibleTechnicalAdvisorMessageEvent.getInterview());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
