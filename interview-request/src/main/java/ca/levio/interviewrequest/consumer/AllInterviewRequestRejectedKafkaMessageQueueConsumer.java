package ca.levio.interviewrequest.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ca.levio.interviewrequest.service.InterviewRequestProcessService;
import ca.levio.messagequeue.messageevent.AllInterviewRequestRejectedMessageEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class AllInterviewRequestRejectedKafkaMessageQueueConsumer {

    private final InterviewRequestProcessService processSchedulerService;

    @KafkaListener(topics = AllInterviewRequestRejectedMessageEvent.TOPIC)
    public void receive(AllInterviewRequestRejectedMessageEvent allInterviewRequestRejectedMessageEvent) {
        log.info("AllInterviewRequestRejectedMessageEvent data {}", allInterviewRequestRejectedMessageEvent);
        processSchedulerService.runNewIterationOfProcess(allInterviewRequestRejectedMessageEvent.getInterview());
    }
}
