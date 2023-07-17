package ca.levio.notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import ca.levio.notification.messageevent.InterviewAlreadyAcceptedMessageEvent;
import ca.levio.notification.common.JsonConverter;
import ca.levio.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class InterviewAlreadyAcceptedKafkaMessageQueueConsumer {

    private NotificationService notificationService;

    @KafkaListener(topics = InterviewAlreadyAcceptedMessageEvent.TOPIC)
    public void receive(String json) {
        try {
            log.info("send notification {}", json);
            InterviewAlreadyAcceptedMessageEvent interviewAlreadyAcceptedMessageEvent = JsonConverter.fromJson(json, InterviewAlreadyAcceptedMessageEvent.class);
            notificationService.sendInterviewAlreadyAcceptedNotification(interviewAlreadyAcceptedMessageEvent);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
