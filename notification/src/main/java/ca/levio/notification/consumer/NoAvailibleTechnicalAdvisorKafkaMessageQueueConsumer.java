package ca.levio.notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ca.levio.messagequeue.messageevent.NoAvailibleTechnicalAdvisorMessageEvent;
import ca.levio.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class NoAvailibleTechnicalAdvisorKafkaMessageQueueConsumer {

    private NotificationService notificationService;

    @KafkaListener(topics = NoAvailibleTechnicalAdvisorMessageEvent.TOPIC)
    public void receive(NoAvailibleTechnicalAdvisorMessageEvent noAvailibleTechnicalAdvisorMessageEvent) {
        log.info("send notification {}", noAvailibleTechnicalAdvisorMessageEvent);
        notificationService.sendNoAvailibleTechnicalAdvisorNotification(noAvailibleTechnicalAdvisorMessageEvent);
    }
}
