package ca.levio.interviewrequest.mapper;

import org.mapstruct.Mapper;

import ca.levio.commonbean.messageevent.InterviewRequestMessageEvent;
import ca.levio.interviewrequest.model.InterviewRequest;

@Mapper(componentModel = "spring")
public abstract class InterviewRequestMessageEventMapper {

    public abstract InterviewRequestMessageEvent interviewRequestToInterviewRequestDto(InterviewRequest interviewRequest);

    public abstract InterviewRequest interviewRequestDtoToInterviewRequest(InterviewRequestMessageEvent interviewRequestMessageEvent);

}
