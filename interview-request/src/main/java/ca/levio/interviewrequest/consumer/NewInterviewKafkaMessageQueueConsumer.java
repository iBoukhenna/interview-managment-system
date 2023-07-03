package ca.levio.interviewrequest.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ca.levio.commonbean.messageevent.NewInterviewMessageEvent;
import ca.levio.interviewrequest.mapper.InterviewRequestNewInterviewMessageEventMapper;
import ca.levio.interviewrequest.model.InterviewRequest;
import ca.levio.interviewrequest.service.InterviewRequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class NewInterviewKafkaMessageQueueConsumer {

    private final InterviewRequestService interviewRequestService;
    private final InterviewRequestNewInterviewMessageEventMapper interviewRequestNewInterviewMessageEventMapper;

    @KafkaListener(topics = NewInterviewMessageEvent.TOPIC)
    public void receive(NewInterviewMessageEvent newInterviewMessageEvent) {
        log.info("receive interivew data from interview {}", newInterviewMessageEvent);
        InterviewRequest interviewRequest = interviewRequestNewInterviewMessageEventMapper.NewInterviewMessageEventToInterviewRequest(newInterviewMessageEvent);
        interviewRequestService.createInterviewRequest(interviewRequest, newInterviewMessageEvent.getX(), newInterviewMessageEvent.getJobPosition(), newInterviewMessageEvent.getLevelOfExpertise());
    }
}
