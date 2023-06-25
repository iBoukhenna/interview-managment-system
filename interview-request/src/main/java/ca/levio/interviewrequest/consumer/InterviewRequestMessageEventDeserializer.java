package ca.levio.interviewrequest.consumer;


import org.springframework.kafka.support.serializer.JsonDeserializer;

import ca.levio.commonbean.messageevent.InterviewRequestMessageEvent;


//Todo : pourquoi coder un deserializer, normalement JsonDeserializer fait directement le job
//Elle est utilisée ou cette classe ?
public class InterviewRequestMessageEventDeserializer extends JsonDeserializer<InterviewRequestMessageEvent> {

	public InterviewRequestMessageEventDeserializer() {
		super(InterviewRequestMessageEvent.class);
	}

}
