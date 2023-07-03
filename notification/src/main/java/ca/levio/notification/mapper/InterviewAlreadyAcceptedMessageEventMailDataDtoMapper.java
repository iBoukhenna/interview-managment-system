package ca.levio.notification.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.levio.commonbean.messageevent.InterviewAlreadyAcceptedMessageEvent;
import ca.levio.mailmaker.maildtos.InterviewAlreadyAcceptedMailDataRequestDto;
import ca.levio.notification.config.LinksConfigProperties;

@Mapper(componentModel = "spring")
public abstract class InterviewAlreadyAcceptedMessageEventMailDataDtoMapper {

    @Mapping(target = "linkInterviewDetail", expression = "java(getLinkInterviewDetail(interviewAlreadyAcceptedMessageEvent.getInterview(), linksConfigProperties))")
    @Mapping(target = "to", source = "interviewAlreadyAcceptedMessageEvent.technicalAdvisorEmail")
    public abstract InterviewAlreadyAcceptedMailDataRequestDto interviewAlreadyAcceptedMessageEventToInterviewAlreadyAcceptedMailDataRequestDto(InterviewAlreadyAcceptedMessageEvent interviewAlreadyAcceptedMessageEvent, LinksConfigProperties linksConfigProperties);

    public abstract InterviewAlreadyAcceptedMessageEvent interviewAlreadyAcceptedMailDataRequestDtoToInterviewAlreadyAcceptedMessageEvent(InterviewAlreadyAcceptedMailDataRequestDto interviewAlreadyAcceptedMailDataRequestDto);

    protected String getLinkInterviewDetail(String interview, LinksConfigProperties linksConfigProperties) {
        return String.format(linksConfigProperties.getInterviewDetailLink(), interview);
    }
}
