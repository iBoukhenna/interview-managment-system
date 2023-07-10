package ca.levio.taskscheduler.mapper;

import org.mapstruct.Mapper;

import ca.levio.commonbean.messageevent.NewIterationOfSelectionMessageEvent;
import ca.levio.commonbean.dto.InterviewDto;

@Mapper(componentModel = "spring")
public abstract class InterviewDtoNewIterationOfSelectionMessageEventMapper {

    public abstract NewIterationOfSelectionMessageEvent interviewDtoToNewIterationOfSelectionMessageEvent(InterviewDto interviewDto);

    public abstract InterviewDto newIterationOfSelectionMessageEventToInterviewDto(NewIterationOfSelectionMessageEvent newIterationOfSelectionMessageEvent);
}
