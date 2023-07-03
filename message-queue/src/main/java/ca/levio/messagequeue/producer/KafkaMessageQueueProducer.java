package ca.levio.messagequeue.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import ca.levio.commonbean.messageevent.NewInterviewMessageEvent;
import ca.levio.commonbean.messageevent.NewInterviewRequestMessageEvent;
import ca.levio.commonbean.messageevent.InterviewAcceptedMessageEvent;
import ca.levio.commonbean.messageevent.InterviewAlreadyAcceptedMessageEvent;
import ca.levio.commonbean.messageevent.InterviewAssignedMessageEvent;
import ca.levio.commonbean.messageevent.InterviewDeclinedMessageEvent;
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
        } else if (data instanceof InterviewAcceptedMessageEvent) {
            kafkaTemplate.send(InterviewAcceptedMessageEvent.TOPIC, data.getId(), data);
        } else if (data instanceof InterviewAlreadyAcceptedMessageEvent) {
            kafkaTemplate.send(InterviewAlreadyAcceptedMessageEvent.TOPIC, data.getId(), data);
        } else if (data instanceof InterviewAssignedMessageEvent) {
            kafkaTemplate.send(InterviewAssignedMessageEvent.TOPIC, data.getId(), data);
        } else if (data instanceof InterviewDeclinedMessageEvent) {
            kafkaTemplate.send(InterviewDeclinedMessageEvent.TOPIC, data.getId(), data);
        }
    }
}
