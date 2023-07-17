package ca.levio.interviewrequest.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import ca.levio.interviewrequest.service.InterviewRequestProcessService;
import ca.levio.interviewrequest.common.JsonConverter;
import ca.levio.interviewrequest.messageevent.AllInterviewRequestRejectedMessageEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class AllInterviewRequestRejectedKafkaMessageQueueConsumer {

    private final InterviewRequestProcessService processSchedulerService;

    @KafkaListener(topics = AllInterviewRequestRejectedMessageEvent.TOPIC)
    public void receive(String json) {
        try {
            log.info("AllInterviewRequestRejectedMessageEvent data {}", json);
            AllInterviewRequestRejectedMessageEvent allInterviewRequestRejectedMessageEvent = JsonConverter.fromJson(json, AllInterviewRequestRejectedMessageEvent.class);
            processSchedulerService.runNewIterationOfProcess(allInterviewRequestRejectedMessageEvent.getInterview());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
