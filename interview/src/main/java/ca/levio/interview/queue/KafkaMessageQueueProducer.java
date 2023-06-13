package ca.levio.interview.queue;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import ca.levio.interview.dto.GenerateInterviewRequestDto;
import ca.levio.interview.mapper.GenerateInterviewRequestDtoMapper;
import ca.levio.interview.model.Interview;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class KafkaMessageQueueProducer implements MessageQueueProducer {

	private KafkaTemplate<String, GenerateInterviewRequestDto> kafkaTemplate;
    private GenerateInterviewRequestDtoMapper generateInterviewRequestDtoMapper;

    @Override
    public void send(Object data) {
        Interview interview = (Interview) data;
        log.info("send interivew data to interview request {}", interview);

        GenerateInterviewRequestDto generateInterviewRequestDto = generateInterviewRequestDtoMapper.interviewToInterviewRequestDto(interview);
        kafkaTemplate.send("interviewRequest.topic", "interviewRequest.createInterviewDto", generateInterviewRequestDto);
    }
}
