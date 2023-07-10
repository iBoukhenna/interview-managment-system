package ca.levio.messagequeue.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import ca.levio.commonbean.messageevent.MessageEvent;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class KafkaMessageQueueProducer implements MessageQueueProducer {

	private KafkaTemplate<String, MessageEvent> kafkaTemplate;

    @Override
    public void send(MessageEvent data) {
        kafkaTemplate.send(data.getClass().getSimpleName(), data.getId(), data);
    }
}
