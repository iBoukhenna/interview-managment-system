package ca.levio.notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ca.levio.commonbean.messageevent.InterviewDeclinedMessageEvent;
import ca.levio.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class InterviewDeclinedKafkaMessageQueueConsumer {

    private NotificationService notificationService;

    @KafkaListener(topics = InterviewDeclinedMessageEvent.TOPIC)
    public void receive(InterviewDeclinedMessageEvent interviewDeclinedMessageEvent) {
        log.info("send notification {}", interviewDeclinedMessageEvent);
        notificationService.sendInterviewDeclinedNotification(interviewDeclinedMessageEvent);
    }
}
