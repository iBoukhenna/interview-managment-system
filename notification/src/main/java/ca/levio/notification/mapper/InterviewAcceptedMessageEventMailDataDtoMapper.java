package ca.levio.notification.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.levio.commonbean.messageevent.InterviewAcceptedMessageEvent;
import ca.levio.mailmaker.maildtos.InterviewAcceptedMailDataRequestDto;
import ca.levio.notification.config.LinksConfigProperties;

@Mapper(componentModel = "spring")
public abstract class InterviewAcceptedMessageEventMailDataDtoMapper {

    @Mapping(target = "linkRecruiterDetail", expression = "java(getLinkRecruiterDetail(interviewAcceptedMessageEvent.getRecruiter(), linksConfigProperties))")
    @Mapping(target = "linkInterviewDetail", expression = "java(getLinkInterviewDetail(interviewAcceptedMessageEvent.getInterview(), linksConfigProperties))")
    @Mapping(target = "to", source = "interviewAcceptedMessageEvent.technicalAdvisorEmail")
    public abstract InterviewAcceptedMailDataRequestDto interviewAcceptedMessageEventToInterviewAcceptedMailDataRequestDto(InterviewAcceptedMessageEvent interviewAcceptedMessageEvent, LinksConfigProperties linksConfigProperties);

    public abstract InterviewAcceptedMessageEvent interviewAcceptedMailDataRequestDtoToInterviewAcceptedMessageEvent(InterviewAcceptedMailDataRequestDto interviewAcceptedMailDataRequestDto);

    protected String getLinkRecruiterDetail(String recruiter, LinksConfigProperties linksConfigProperties) {
        return String.format(linksConfigProperties.getRecruiterDetailLink(), recruiter);
    }

    protected String getLinkInterviewDetail(String interview, LinksConfigProperties linksConfigProperties) {
        return String.format(linksConfigProperties.getInterviewDetailLink(), interview);
    }
}
