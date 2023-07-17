package ca.levio.interviewrequest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.levio.interviewrequest.model.InterviewRequest;
import ca.levio.interviewrequest.messageevent.NewInterviewRequestMessageEvent;

@Mapper(componentModel = "spring")
public abstract class InterviewRequestNewInterviewRequestMessageEventMapper {
    
    @Mapping(target = "interviewRequest", source = "interviewRequest.id")
    public abstract NewInterviewRequestMessageEvent interviewRequestToNewInterviewRequestMessageEvent(InterviewRequest interviewRequest);

    @Mapping(target = "id", source = "newInterviewRequestMessageEvent.interviewRequest")
    public abstract InterviewRequest newInterviewRequestMessageEventToInterviewRequest(NewInterviewRequestMessageEvent newInterviewRequestMessageEvent);
}
