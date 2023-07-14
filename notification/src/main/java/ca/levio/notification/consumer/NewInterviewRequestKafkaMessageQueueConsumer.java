package ca.levio.notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ca.levio.messagequeue.messageevent.NewInterviewRequestMessageEvent;
import ca.levio.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class NewInterviewRequestKafkaMessageQueueConsumer {

    private NotificationService notificationService;

    @KafkaListener(topics = NewInterviewRequestMessageEvent.TOPIC)
    public void receive(NewInterviewRequestMessageEvent newInterviewRequestMessageEvent) {
        log.info("send notification {}", newInterviewRequestMessageEvent);
        notificationService.sendNewInterviewRequestNotification(newInterviewRequestMessageEvent);
    }
}
