package ca.levio.notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import ca.levio.notification.messageevent.InterviewDeclinedMessageEvent;
import ca.levio.notification.common.JsonConverter;
import ca.levio.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class InterviewDeclinedKafkaMessageQueueConsumer {

    private NotificationService notificationService;

    @KafkaListener(topics = InterviewDeclinedMessageEvent.TOPIC)
    public void receive(String json) {
        try {
            log.info("send notification {}", json);
            InterviewDeclinedMessageEvent interviewDeclinedMessageEvent = JsonConverter.fromJson(json, InterviewDeclinedMessageEvent.class);
            notificationService.sendInterviewDeclinedNotification(interviewDeclinedMessageEvent);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
