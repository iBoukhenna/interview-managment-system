package ca.levio.interview.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import ca.levio.interview.dto.InterviewRequestCreationRequest;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaMessageProducer {
    
    private final KafkaTemplate<String, InterviewRequestCreationRequest> kafkaTemplate;

    public void publish(String topic, String key, InterviewRequestCreationRequest data) {
        log.info("Publishing to {} using Key {}. Data: {}", topic, key, data);
        kafkaTemplate.send(topic, key, data);
        log.info("Published to {} using Key {}. Data: {}", topic, key, data);
    }
}
