package ca.levio.interviewrequest.queue;


import org.springframework.kafka.support.serializer.JsonDeserializer;

import ca.levio.interview.dto.InterviewRequestDto;


public class InterviewRequestDtoDeserializer extends JsonDeserializer<InterviewRequestDto> {

	public InterviewRequestDtoDeserializer() {
		super(InterviewRequestDto.class);
	}

}
