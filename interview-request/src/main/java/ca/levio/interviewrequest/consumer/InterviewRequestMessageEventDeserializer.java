package ca.levio.interviewrequest.consumer;


import org.springframework.kafka.support.serializer.JsonDeserializer;

import ca.levio.commonbean.messageevent.InterviewRequestMessageEvent;


public class InterviewRequestMessageEventDeserializer extends JsonDeserializer<InterviewRequestMessageEvent> {

	public InterviewRequestMessageEventDeserializer() {
		super(InterviewRequestMessageEvent.class);
	}

}