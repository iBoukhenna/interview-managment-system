package ca.levio.notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ca.levio.commonbean.messageevent.InterviewAssignedMessageEvent;
import ca.levio.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class InterviewAssignedKafkaMessageQueueConsumer {

    private NotificationService notificationService;

    @KafkaListener(topics = InterviewAssignedMessageEvent.TOPIC)
    public void receive(InterviewAssignedMessageEvent interviewAssignedMessageEvent) {
        log.info("send notification {}", interviewAssignedMessageEvent);
        notificationService.sendInterviewAssignedNotification(interviewAssignedMessageEvent);
    }
}
