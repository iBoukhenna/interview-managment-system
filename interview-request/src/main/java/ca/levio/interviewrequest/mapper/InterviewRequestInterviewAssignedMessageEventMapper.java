package ca.levio.interviewrequest.mapper;

import org.mapstruct.Mapper;

import ca.levio.interviewrequest.model.InterviewRequest;
import ca.levio.messagequeue.messageevent.InterviewAssignedMessageEvent;

@Mapper(componentModel = "spring")
public abstract class InterviewRequestInterviewAssignedMessageEventMapper {
    
    public abstract InterviewAssignedMessageEvent interviewRequestToInterviewAssignedMessageEvent(InterviewRequest interviewRequest);

    public abstract InterviewRequest interviewAssignedMessageEventToInterviewRequest(InterviewAssignedMessageEvent interviewAssignedMessageEvent);
}
