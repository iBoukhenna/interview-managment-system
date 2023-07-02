package ca.levio.messagequeue.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import ca.levio.commonbean.messageevent.NewInterviewMessageEvent;
import ca.levio.commonbean.messageevent.NewInterviewRequestMessageEvent;
import ca.levio.commonbean.messageevent.MessageEvent;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class KafkaMessageQueueProducer implements MessageQueueProducer {

	private KafkaTemplate<String, MessageEvent> kafkaTemplate;

    @Override
    public void send(MessageEvent data) {
        if (data instanceof NewInterviewMessageEvent) {
            kafkaTemplate.send(NewInterviewMessageEvent.TOPIC, data.getId(), data);
        } else if (data instanceof NewInterviewRequestMessageEvent) {
            kafkaTemplate.send(NewInterviewRequestMessageEvent.TOPIC, data.getId(), data);
        }
    }
}
