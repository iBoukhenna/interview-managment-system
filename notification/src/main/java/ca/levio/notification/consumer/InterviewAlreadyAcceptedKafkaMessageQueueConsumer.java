package ca.levio.notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ca.levio.messagequeue.messageevent.InterviewAlreadyAcceptedMessageEvent;
import ca.levio.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class InterviewAlreadyAcceptedKafkaMessageQueueConsumer {

    private NotificationService notificationService;

    @KafkaListener(topics = InterviewAlreadyAcceptedMessageEvent.TOPIC)
    public void receive(InterviewAlreadyAcceptedMessageEvent interviewAlreadyAcceptedMessageEvent) {
        log.info("send notification {}", interviewAlreadyAcceptedMessageEvent);
        notificationService.sendInterviewAlreadyAcceptedNotification(interviewAlreadyAcceptedMessageEvent);
    }
}
