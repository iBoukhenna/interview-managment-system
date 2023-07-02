package ca.levio.interviewrequest.mapper;

import org.mapstruct.Mapper;

import ca.levio.commonbean.messageevent.NewInterviewMessageEvent;
import ca.levio.interviewrequest.model.InterviewRequest;

@Mapper(componentModel = "spring")
public abstract class InterviewRequestNewInterviewMessageEventMapper {

    public abstract NewInterviewMessageEvent interviewRequestToNewInterviewMessageEvent(InterviewRequest interviewRequest);

    public abstract InterviewRequest NewInterviewMessageEventToInterviewRequest(NewInterviewMessageEvent newInterviewMessageEvent);

}
