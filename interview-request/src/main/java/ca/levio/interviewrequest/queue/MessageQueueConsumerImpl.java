package ca.levio.interviewrequest.queue;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ca.levio.interview.dto.InterviewRequestDto;
import ca.levio.interviewrequest.mapper.InterviewRequestMapper;
import ca.levio.interviewrequest.model.InterviewRequest;
import ca.levio.interviewrequest.service.InterviewRequestService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MessageQueueConsumerImpl {

    private InterviewRequestService interviewRequestService;
    private InterviewRequestMapper interviewRequestMapper;

    @KafkaListener(topics = "interviewRequest.topic", groupId = "interviewRequestId")
    public void receive(InterviewRequestDto interviewRequestDto) {
        InterviewRequest interviewRequest = interviewRequestMapper.interviewRequestDtoToInterviewRequest(interviewRequestDto);
        interviewRequestService.createInterviewRequest(interviewRequest, interviewRequestDto.getX(), interviewRequestDto.getJobPosition(), interviewRequestDto.getLevelOfExpertise());
    }
}
