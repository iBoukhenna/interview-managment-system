package ca.levio.interviewrequest.queue;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ca.levio.interview.dto.GenerateInterviewRequestDto;
import ca.levio.interviewrequest.mapper.CreateInterviewRequestMapper;
import ca.levio.interviewrequest.model.InterviewRequest;
import ca.levio.interviewrequest.service.InterviewRequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class KafkaMessageQueueConsumer {

    private InterviewRequestService interviewRequestService;
    private CreateInterviewRequestMapper interviewRequestMapper;

    @KafkaListener(topics = "interviewRequest.topic", groupId = "interviewRequestId")
    public void receive(GenerateInterviewRequestDto interviewRequestDto) {
        log.info("receive interivew data from interview {}", interviewRequestDto);
        InterviewRequest interviewRequest = interviewRequestMapper.interviewRequestDtoToInterviewRequest(interviewRequestDto);
        interviewRequestService.createInterviewRequest(interviewRequest, interviewRequestDto.getX(), interviewRequestDto.getJobPosition(), interviewRequestDto.getLevelOfExpertise());
    }
}
