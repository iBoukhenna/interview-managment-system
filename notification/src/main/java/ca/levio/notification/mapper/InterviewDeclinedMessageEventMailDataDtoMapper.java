package ca.levio.notification.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.levio.commonbean.messageevent.InterviewDeclinedMessageEvent;
import ca.levio.mailmaker.maildtos.InterviewDeclinedMailDataRequestDto;
import ca.levio.notification.config.LinksConfigProperties;

@Mapper(componentModel = "spring")
public abstract class InterviewDeclinedMessageEventMailDataDtoMapper {

    @Mapping(target = "linkInterviewDetail", expression = "java(getLinkInterviewDetail(interviewDeclinedMessageEvent.getInterview(), linksConfigProperties))")
    @Mapping(target = "to", source = "interviewDeclinedMessageEvent.technicalAdvisorEmail")
    public abstract InterviewDeclinedMailDataRequestDto interviewDeclinedMessageEventToInterviewDeclinedMailDataRequestDto(InterviewDeclinedMessageEvent interviewDeclinedMessageEvent, LinksConfigProperties linksConfigProperties);

    public abstract InterviewDeclinedMessageEvent interviewDeclinedMailDataRequestDtoToInterviewDeclinedMessageEvent(InterviewDeclinedMailDataRequestDto interviewDeclinedMailDataRequestDto);

    protected String getLinkInterviewDetail(String interview, LinksConfigProperties linksConfigProperties) {
        return String.format(linksConfigProperties.getInterviewDetailLink(), interview);
    }
}
