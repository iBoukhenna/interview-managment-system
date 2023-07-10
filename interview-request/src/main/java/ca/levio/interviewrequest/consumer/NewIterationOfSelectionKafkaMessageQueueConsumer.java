package ca.levio.interviewrequest.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ca.levio.commonbean.messageevent.NewIterationOfSelectionMessageEvent;
import ca.levio.interviewrequest.mapper.InterviewRequestNewIterationOfSelectionMessageEventMapper;
import ca.levio.interviewrequest.model.InterviewRequest;
import ca.levio.interviewrequest.service.InterviewRequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class NewIterationOfSelectionKafkaMessageQueueConsumer {

    private final InterviewRequestService interviewRequestService;
    private final InterviewRequestNewIterationOfSelectionMessageEventMapper interviewRequestNewIterationOfSelectionMessageEventMapper;

    @KafkaListener(topics = NewIterationOfSelectionMessageEvent.TOPIC)
    public void receive(NewIterationOfSelectionMessageEvent newIterationOfSelectionMessageEvent) {
        log.info("receive interivew data from interview {}", newIterationOfSelectionMessageEvent);
        InterviewRequest interviewRequest = interviewRequestNewIterationOfSelectionMessageEventMapper.newIterationOfSelectionMessageEventToInterviewRequest(newIterationOfSelectionMessageEvent);
        interviewRequestService.createInterviewRequest(interviewRequest, newIterationOfSelectionMessageEvent.getNumberOfTechnicalAdvisorByBatch(), newIterationOfSelectionMessageEvent.getJobPosition(), newIterationOfSelectionMessageEvent.getLevelOfExpertise());
    }
}
