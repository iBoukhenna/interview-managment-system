package ca.levio.interview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.levio.commonbean.messageevent.NewInterviewMessageEvent;
import ca.levio.interview.model.Interview;

@Mapper(componentModel = "spring")
public abstract class InterviewNewInterviewMessageEventMapper {

    @Mapping(target = "interview", source = "interview.id")
    @Mapping(target = "numberOfTechnicalAdvisorByBatch", source = "interview.typeOfInterview.numberOfTechnicalAdvisorByBatch")
    public abstract NewInterviewMessageEvent interviewToNewInterviewMessageEvent(Interview interview);

    @Mapping(target = "id", source = "newInterviewMessageEvent.interview")
    @Mapping(target = "typeOfInterview", expression = "java(ca.levio.interview.enums.TypeOfInterview.getTypeOfInterviewFromNumberOfTechnicalAdvisorByBatch(newInterviewMessageEvent.getNumberOfTechnicalAdvisorByBatch()))")
    public abstract Interview NewInterviewMessageEventToInterview(NewInterviewMessageEvent newInterviewMessageEvent);
}
