package ca.levio.notification.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.levio.commonbean.messageevent.InterviewAssignedMessageEvent;
import ca.levio.mailmaker.maildtos.InterviewAssignedMailDataRequestDto;
import ca.levio.notification.config.LinksConfigProperties;

@Mapper(componentModel = "spring")
public abstract class InterviewAssignedMessageEventMailDataDtoMapper {

    @Mapping(target = "linkTechnicalAdvisorDetail", expression = "java(getLinkTechnicalAdvisorDetail(interviewAssignedMessageEvent.getTechnicalAdvisor(), linksConfigProperties))")
    @Mapping(target = "linkInterviewDetail", expression = "java(getLinkInterviewDetail(interviewAssignedMessageEvent.getInterview(), linksConfigProperties))")
    @Mapping(target = "to", source = "interviewAssignedMessageEvent.recruiterEmail")
    public abstract InterviewAssignedMailDataRequestDto interviewAssignedMessageEventToInterviewAssignedMailDataRequestDto(InterviewAssignedMessageEvent interviewAssignedMessageEvent, LinksConfigProperties linksConfigProperties);

    public abstract InterviewAssignedMessageEvent interviewAssignedMailDataRequestDtoToInterviewAssignedMessageEvent(InterviewAssignedMailDataRequestDto interviewAssignedMailDataRequestDto);

    protected String getLinkTechnicalAdvisorDetail(String technicalAdvisor, LinksConfigProperties linksConfigProperties) {
        return String.format(linksConfigProperties.getTechnicalAdvisorDetailLink(), technicalAdvisor);
    }

    protected String getLinkInterviewDetail(String interview, LinksConfigProperties linksConfigProperties) {
        return String.format(linksConfigProperties.getInterviewDetailLink(), interview);
    }
}
