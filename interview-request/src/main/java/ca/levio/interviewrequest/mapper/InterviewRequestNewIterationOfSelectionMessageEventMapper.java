package ca.levio.interviewrequest.mapper;

import org.mapstruct.Mapper;

import ca.levio.commonbean.messageevent.NewIterationOfSelectionMessageEvent;
import ca.levio.interviewrequest.model.InterviewRequest;

@Mapper(componentModel = "spring")
public abstract class InterviewRequestNewIterationOfSelectionMessageEventMapper {

    public abstract NewIterationOfSelectionMessageEvent interviewRequestToNewIterationOfSelectionMessageEvent(InterviewRequest interviewRequest);

    public abstract InterviewRequest newIterationOfSelectionMessageEventToInterviewRequest(NewIterationOfSelectionMessageEvent newIterationOfSelectionMessageEvent);

}
