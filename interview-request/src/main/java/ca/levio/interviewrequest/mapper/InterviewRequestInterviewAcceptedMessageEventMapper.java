package ca.levio.interviewrequest.mapper;

import org.mapstruct.Mapper;

import ca.levio.interviewrequest.model.InterviewRequest;
import ca.levio.interviewrequest.messageevent.InterviewAcceptedMessageEvent;

@Mapper(componentModel = "spring")
public abstract class InterviewRequestInterviewAcceptedMessageEventMapper {
    
    public abstract InterviewAcceptedMessageEvent interviewRequestToInterviewAcceptedMessageEvent(InterviewRequest interviewRequest);

    public abstract InterviewRequest interviewAcceptedMessageEventToInterviewRequest(InterviewAcceptedMessageEvent interviewAcceptedMessageEvent);
}
