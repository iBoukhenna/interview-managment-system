package ca.levio.notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import ca.levio.notification.messageevent.InterviewAcceptedMessageEvent;
import ca.levio.notification.common.JsonConverter;
import ca.levio.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class InterviewAcceptedKafkaMessageQueueConsumer {

    private NotificationService notificationService;

    @KafkaListener(topics = InterviewAcceptedMessageEvent.TOPIC)
    public void receive(String json) {
        try {
            log.info("send notification {}", json);
            InterviewAcceptedMessageEvent interviewAcceptedMessageEvent = JsonConverter.fromJson(json, InterviewAcceptedMessageEvent.class);
            notificationService.sendInterviewAcceptedNotification(interviewAcceptedMessageEvent);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
