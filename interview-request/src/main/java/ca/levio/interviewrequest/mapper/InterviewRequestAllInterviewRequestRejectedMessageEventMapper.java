package ca.levio.interviewrequest.mapper;

import org.mapstruct.Mapper;

import ca.levio.interviewrequest.model.InterviewRequest;
import ca.levio.messagequeue.messageevent.AllInterviewRequestRejectedMessageEvent;

@Mapper(componentModel = "spring")
public abstract class InterviewRequestAllInterviewRequestRejectedMessageEventMapper {

    public abstract AllInterviewRequestRejectedMessageEvent interviewRequestToAllInterviewRequestRejectedMessageEvent(InterviewRequest interviewRequest);

    public abstract InterviewRequest allInterviewRequestRejectedMessageEventToInterviewRequest(AllInterviewRequestRejectedMessageEvent allInterviewRequestRejectedMessageEvent);

}
