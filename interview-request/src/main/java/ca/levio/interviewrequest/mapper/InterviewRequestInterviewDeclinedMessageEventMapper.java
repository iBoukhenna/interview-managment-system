package ca.levio.interviewrequest.mapper;

import org.mapstruct.Mapper;

import ca.levio.interviewrequest.model.InterviewRequest;
import ca.levio.messagequeue.messageevent.InterviewDeclinedMessageEvent;

@Mapper(componentModel = "spring")
public abstract class InterviewRequestInterviewDeclinedMessageEventMapper {
    
    public abstract InterviewDeclinedMessageEvent interviewRequestToInterviewDeclinedMessageEvent(InterviewRequest interviewRequest);

    public abstract InterviewRequest interviewDeclinedMessageEventToInterviewRequest(InterviewDeclinedMessageEvent interviewDeclinedMessageEvent);
}
