package ca.levio.notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import ca.levio.notification.messageevent.NewInterviewRequestMessageEvent;
import ca.levio.notification.common.JsonConverter;
import ca.levio.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class NewInterviewRequestKafkaMessageQueueConsumer {

    private NotificationService notificationService;

    @KafkaListener(topics = NewInterviewRequestMessageEvent.TOPIC)
    public void receive(String json) {
        try {
            log.info("send notification {}", json);
            NewInterviewRequestMessageEvent newInterviewRequestMessageEvent = JsonConverter.fromJson(json, NewInterviewRequestMessageEvent.class);
            notificationService.sendNewInterviewRequestNotification(newInterviewRequestMessageEvent);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
