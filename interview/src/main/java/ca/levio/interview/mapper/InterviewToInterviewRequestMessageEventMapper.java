package ca.levio.interview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.levio.commonbean.messageevent.InterviewRequestMessageEvent;
import ca.levio.interview.model.Interview;

@Mapper(componentModel = "spring")
public abstract class InterviewToInterviewRequestMessageEventMapper {

    @Mapping(target = "interview", expression = "java(interview.getId())")
    @Mapping(target = "x", expression = "java(interview.getTypeOfInterview().getX())")
    public abstract InterviewRequestMessageEvent interviewToInterviewRequestDto(Interview interview);

    @Mapping(target = "id", expression = "java(interviewRequestMessageEvent.getInterview())")
    @Mapping(target = "typeOfInterview", expression = "java(ca.levio.interview.enums.TypeOfInterview.getTypeOfInterviewFromX(interviewRequestMessageEvent.getX()))")
    public abstract Interview InterviewRequestDtoToInterview(InterviewRequestMessageEvent interviewRequestMessageEvent);
}
