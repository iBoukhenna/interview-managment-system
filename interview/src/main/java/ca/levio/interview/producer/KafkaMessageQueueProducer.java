package ca.levio.interview.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import ca.levio.interview.common.JsonConverter;
import ca.levio.interview.messageevent.MessageEvent;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class KafkaMessageQueueProducer implements MessageQueueProducer {

	private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(MessageEvent data) {
        try {
            String json = JsonConverter.toJson(data);
            kafkaTemplate.send(data.getClass().getSimpleName(), data.getId(), json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
