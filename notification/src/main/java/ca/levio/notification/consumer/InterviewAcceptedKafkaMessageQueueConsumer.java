package ca.levio.notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ca.levio.commonbean.messageevent.InterviewAcceptedMessageEvent;
import ca.levio.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class InterviewAcceptedKafkaMessageQueueConsumer {

    private NotificationService notificationService;

    @KafkaListener(topics = InterviewAcceptedMessageEvent.TOPIC)
    public void receive(InterviewAcceptedMessageEvent interviewAcceptedMessageEvent) {
        log.info("send notification {}", interviewAcceptedMessageEvent);
        notificationService.sendInterviewAcceptedNotification(interviewAcceptedMessageEvent);
    }
}
