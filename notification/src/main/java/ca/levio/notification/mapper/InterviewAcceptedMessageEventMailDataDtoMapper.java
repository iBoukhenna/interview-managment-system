package ca.levio.notification.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.levio.notification.messageevent.InterviewAcceptedMessageEvent;
import ca.levio.notification.config.LinksConfigProperties;
import ca.levio.notification.dto.TechnicalAdvisorDto;
import ca.levio.notification.maildtos.InterviewAcceptedMailDataRequestDto;
import ca.levio.notification.service.TechnicalAdvisorService;

@Mapper(componentModel = "spring")
public abstract class InterviewAcceptedMessageEventMailDataDtoMapper {

    @Mapping(target = "linkRecruiterDetail", expression = "java(getLinkRecruiterDetail(interviewAcceptedMessageEvent.getRecruiter(), linksConfigProperties))")
    @Mapping(target = "linkInterviewDetail", expression = "java(getLinkInterviewDetail(interviewAcceptedMessageEvent.getInterview(), linksConfigProperties))")
    @Mapping(target = "to", expression = "java(getTechnicalAdvisorEmail(interviewAcceptedMessageEvent.getTechnicalAdvisor(), technicalAdvisorService))")
    @Mapping(target = "technicalAdvisorName", expression = "java(getTechnicalAdvisorName(interviewAcceptedMessageEvent.getTechnicalAdvisor(), technicalAdvisorService))")
    public abstract InterviewAcceptedMailDataRequestDto interviewAcceptedMessageEventToInterviewAcceptedMailDataRequestDto(InterviewAcceptedMessageEvent interviewAcceptedMessageEvent, LinksConfigProperties linksConfigProperties, TechnicalAdvisorService technicalAdvisorService);

    public abstract InterviewAcceptedMessageEvent interviewAcceptedMailDataRequestDtoToInterviewAcceptedMessageEvent(InterviewAcceptedMailDataRequestDto interviewAcceptedMailDataRequestDto);

    protected String getLinkRecruiterDetail(String recruiter, LinksConfigProperties linksConfigProperties) {
        return String.format(linksConfigProperties.getRecruiterDetailLink(), recruiter);
    }

    protected String getLinkInterviewDetail(String interview, LinksConfigProperties linksConfigProperties) {
        return String.format(linksConfigProperties.getInterviewDetailLink(), interview);
    }

    protected String getTechnicalAdvisorEmail(String technicalAdvisor, TechnicalAdvisorService technicalAdvisorService) {
        return getTechnicalAdvisor(technicalAdvisor, technicalAdvisorService).getEmail();
    }

    protected String getTechnicalAdvisorName(String technicalAdvisor, TechnicalAdvisorService technicalAdvisorService) {
        return getTechnicalAdvisor(technicalAdvisor, technicalAdvisorService).getName();
    }

    private TechnicalAdvisorDto getTechnicalAdvisor(String technicalAdvisor, TechnicalAdvisorService technicalAdvisorService) {
        return technicalAdvisorService.getTechnicalAdvisor(technicalAdvisor);
    }
}
