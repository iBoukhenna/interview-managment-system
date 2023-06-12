package ca.levio.interview.queue;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import ca.levio.interview.dto.InterviewRequestDto;
import ca.levio.interview.model.Interview;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MessageQueueProducerImpl implements MessageQueueProducer {

	private KafkaTemplate<String, InterviewRequestDto> kafkaTemplate;

    @Override
    public void send(Object data) {
        Interview interview = (Interview) data;

        InterviewRequestDto interviewRequestDto = new InterviewRequestDto(
            interview.getId(),
            interview.getTypeOfInterview().getX(),
            interview.getJobPosition(),
            interview.getLevelOfExpertise().toString()
        );

        kafkaTemplate.send("interviewRequest.topic", "interviewRequest.createInterviewDto", interviewRequestDto);
    }
}
