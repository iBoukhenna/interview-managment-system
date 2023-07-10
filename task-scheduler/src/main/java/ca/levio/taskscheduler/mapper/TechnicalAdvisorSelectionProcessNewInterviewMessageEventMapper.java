package ca.levio.taskscheduler.mapper;

import org.mapstruct.Mapper;

import ca.levio.commonbean.messageevent.NewInterviewMessageEvent;
import ca.levio.taskscheduler.model.TechnicalAdvisorSelectionProcess;

@Mapper(componentModel = "spring")
public abstract class TechnicalAdvisorSelectionProcessNewInterviewMessageEventMapper {

    public abstract NewInterviewMessageEvent interviewRequestToNewInterviewMessageEvent(TechnicalAdvisorSelectionProcess interviewRequest);

    public abstract TechnicalAdvisorSelectionProcess NewInterviewMessageEventToTechnicalAdvisorSelectionProcess(NewInterviewMessageEvent newInterviewMessageEvent);

}
