package ca.levio.interviewrequest.mapper;

import org.mapstruct.Mapper;

import ca.levio.interviewrequest.dto.InterviewDto;
import ca.levio.messagequeue.messageevent.NewInterviewMessageEvent;

@Mapper(componentModel = "spring")
public abstract class InterviewDtoNewInterviewMessageEventMapper {

    public abstract NewInterviewMessageEvent interviewDtoToNewInterviewMessageEvent(InterviewDto interviewDto);

    public abstract InterviewDto newInterviewMessageEventToInterviewDto(NewInterviewMessageEvent newInterviewMessageEvent);
}
