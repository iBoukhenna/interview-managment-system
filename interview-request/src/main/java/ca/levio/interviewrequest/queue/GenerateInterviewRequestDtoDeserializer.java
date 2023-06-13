package ca.levio.interviewrequest.queue;


import org.springframework.kafka.support.serializer.JsonDeserializer;

import ca.levio.interview.dto.GenerateInterviewRequestDto;


public class GenerateInterviewRequestDtoDeserializer extends JsonDeserializer<GenerateInterviewRequestDto> {

	public GenerateInterviewRequestDtoDeserializer() {
		super(GenerateInterviewRequestDto.class);
	}

}
