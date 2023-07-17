package ca.levio.interviewrequest.mapper;

import org.mapstruct.Mapper;

import ca.levio.interviewrequest.model.TechnicalAdvisorSelectionProcess;
import ca.levio.interviewrequest.messageevent.NewInterviewMessageEvent;

@Mapper(componentModel = "spring")
public abstract class TechnicalAdvisorSelectionProcessNewInterviewMessageEventMapper {

    public abstract NewInterviewMessageEvent interviewRequestToNewInterviewMessageEvent(TechnicalAdvisorSelectionProcess interviewRequest);

    public abstract TechnicalAdvisorSelectionProcess NewInterviewMessageEventToTechnicalAdvisorSelectionProcess(NewInterviewMessageEvent newInterviewMessageEvent);

}
