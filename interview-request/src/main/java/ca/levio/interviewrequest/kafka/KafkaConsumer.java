package ca.levio.interviewrequest.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ca.levio.interviewrequest.dto.InterviewRequestCreationRequest;
import ca.levio.interviewrequest.service.InterviewRequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final InterviewRequestService interviewRequestService;

    @KafkaListener(topics = "interviewrequest.topic", groupId = "notificationId")
    void listener(InterviewRequestCreationRequest data) {
        System.out.println("Listener received: " + data.jobPosition());
        interviewRequestService.createInterviewRequest(data);
    }
}
