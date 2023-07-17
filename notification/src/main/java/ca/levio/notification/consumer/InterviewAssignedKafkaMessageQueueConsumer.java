package ca.levio.notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import ca.levio.notification.messageevent.InterviewAssignedMessageEvent;
import ca.levio.notification.common.JsonConverter;
import ca.levio.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class InterviewAssignedKafkaMessageQueueConsumer {

    private NotificationService notificationService;

    @KafkaListener(topics = InterviewAssignedMessageEvent.TOPIC)
    public void receive(String json) {
        try {
            log.info("send notification {}", json);
            InterviewAssignedMessageEvent interviewAssignedMessageEvent = JsonConverter.fromJson(json, InterviewAssignedMessageEvent.class);
            notificationService.sendInterviewAssignedNotification(interviewAssignedMessageEvent);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
