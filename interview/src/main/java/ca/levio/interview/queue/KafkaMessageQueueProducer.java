package ca.levio.interview.queue;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class KafkaMessageQueueProducer<T> implements MessageQueueProducer<T> {

	private KafkaTemplate<String, T> kafkaTemplate;

    @Override
    public void send(T data) {
        kafkaTemplate.send("interviewRequest.topic", "interviewRequest.interviewDto", data);
    }
}
