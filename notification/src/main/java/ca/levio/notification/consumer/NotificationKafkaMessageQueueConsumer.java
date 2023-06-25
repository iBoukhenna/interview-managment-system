package ca.levio.notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ca.levio.commonbean.messageevent.InterviewRequestMessageEvent;
import ca.levio.commonbean.messageevent.NotificationMessageEvent;
import ca.levio.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class NotificationKafkaMessageQueueConsumer {

    private NotificationService notificationService;

    @KafkaListener(topics = NotificationMessageEvent.TOPIC)
    public void receive(NotificationMessageEvent notificationMessageEvent) {
        log.info("send notification {}", notificationMessageEvent);
        notificationService.send(notificationMessageEvent);
    }
}
