package ca.levio.interview.queue;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import ca.levio.interview.dto.InterviewRequestDto;
import ca.levio.interview.model.Interview;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class MessageQueueProducerImpl implements MessageQueueProducer {

	private KafkaTemplate<String, InterviewRequestDto> kafkaTemplate;

    @Override
    public void send(Object data) {
        Interview interview = (Interview) data;
        log.info("send data of interview {}", interview);

        InterviewRequestDto interviewRequestDto = new InterviewRequestDto(
            interview.getId(),
            interview.getTypeOfInterview().getX(),
            interview.getJobPosition(),
            interview.getLevelOfExpertise().toString()
        );
        log.info("send interivew data to interview request {}", interviewRequestDto);

        kafkaTemplate.send("interviewRequest.topic", "interviewRequest.createInterviewDto", interviewRequestDto);
    }
}
