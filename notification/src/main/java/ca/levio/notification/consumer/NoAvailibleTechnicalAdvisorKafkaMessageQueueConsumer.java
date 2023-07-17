package ca.levio.notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import ca.levio.notification.messageevent.NoAvailibleTechnicalAdvisorMessageEvent;
import ca.levio.notification.common.JsonConverter;
import ca.levio.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class NoAvailibleTechnicalAdvisorKafkaMessageQueueConsumer {

    private NotificationService notificationService;

    @KafkaListener(topics = NoAvailibleTechnicalAdvisorMessageEvent.TOPIC)
    public void receive(String json) {
        try {
            log.info("send notification {}", json);
            NoAvailibleTechnicalAdvisorMessageEvent noAvailibleTechnicalAdvisorMessageEvent = JsonConverter.fromJson(json, NoAvailibleTechnicalAdvisorMessageEvent.class);
            notificationService.sendNoAvailibleTechnicalAdvisorNotification(noAvailibleTechnicalAdvisorMessageEvent);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
