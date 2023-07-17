package ca.levio.interview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.levio.interview.model.Interview;
import ca.levio.interview.messageevent.NewInterviewMessageEvent;

@Mapper(componentModel = "spring")
public abstract class InterviewNewInterviewMessageEventMapper {

    @Mapping(target = "interview", source = "interview.id")
    @Mapping(target = "numberOfTechnicalAdvisorByBatch", source = "interview.typeOfInterview.numberOfTechnicalAdvisorByBatch")
    @Mapping(target = "delayBeforeRetrying", source = "interview.typeOfInterview.delayBeforeRetrying")
    public abstract NewInterviewMessageEvent interviewToNewInterviewMessageEvent(Interview interview);

    @Mapping(target = "id", source = "newInterviewMessageEvent.interview")
    public abstract Interview NewInterviewMessageEventToInterview(NewInterviewMessageEvent newInterviewMessageEvent);
}
