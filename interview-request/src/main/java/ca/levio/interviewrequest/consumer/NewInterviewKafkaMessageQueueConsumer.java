package ca.levio.interviewrequest.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import ca.levio.interviewrequest.common.JsonConverter;
import ca.levio.interviewrequest.dto.InterviewDto;
import ca.levio.interviewrequest.mapper.InterviewDtoNewInterviewMessageEventMapper;
import ca.levio.interviewrequest.service.InterviewRequestProcessService;
import ca.levio.interviewrequest.messageevent.NewInterviewMessageEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class NewInterviewKafkaMessageQueueConsumer {

    private final InterviewRequestProcessService interviewRequestProcessService;
    private final InterviewDtoNewInterviewMessageEventMapper interviewDtoNewInterviewMessageEventMapper;

    @KafkaListener(topics = NewInterviewMessageEvent.TOPIC)
    public void receive(String json) {
        try {
            log.info("NewInterviewMessageEvent data {}", json);
            NewInterviewMessageEvent newInterviewMessageEvent = JsonConverter.fromJson(json, NewInterviewMessageEvent.class);
            InterviewDto interviewDto = interviewDtoNewInterviewMessageEventMapper.newInterviewMessageEventToInterviewDto(newInterviewMessageEvent);
            interviewRequestProcessService.createInterviewRequestsProcess(interviewDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
