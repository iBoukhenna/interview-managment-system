package ca.levio.interviewrequest.mapper;

import org.mapstruct.Mapper;

import ca.levio.interviewrequest.dto.InterviewDto;
import ca.levio.interviewrequest.messageevent.NoAvailibleTechnicalAdvisorMessageEvent;

@Mapper(componentModel = "spring")
public abstract class InterviewDtoNoAvailibleTechnicalAdvisorMessageEventMapper {

    public abstract NoAvailibleTechnicalAdvisorMessageEvent interviewDtoToNoAvailibleTechnicalAdvisorMessageEvent(InterviewDto interviewDto);

    public abstract InterviewDto noAvailibleTechnicalAdvisorMessageEventToInterviewDto(NoAvailibleTechnicalAdvisorMessageEvent noAvailibleTechnicalAdvisorMessageEvent);
}
