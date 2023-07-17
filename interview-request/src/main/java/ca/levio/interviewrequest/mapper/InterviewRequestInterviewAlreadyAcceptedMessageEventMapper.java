package ca.levio.interviewrequest.mapper;

import org.mapstruct.Mapper;

import ca.levio.interviewrequest.model.InterviewRequest;
import ca.levio.interviewrequest.messageevent.InterviewAlreadyAcceptedMessageEvent;

@Mapper(componentModel = "spring")
public abstract class InterviewRequestInterviewAlreadyAcceptedMessageEventMapper {
    
    public abstract InterviewAlreadyAcceptedMessageEvent interviewRequestToInterviewAlreadyAcceptedMessageEvent(InterviewRequest interviewRequest);

    public abstract InterviewRequest interviewAlreadyAcceptedMessageEventToInterviewRequest(InterviewAlreadyAcceptedMessageEvent interviewAlreadyAcceptedMessageEvent);
}
