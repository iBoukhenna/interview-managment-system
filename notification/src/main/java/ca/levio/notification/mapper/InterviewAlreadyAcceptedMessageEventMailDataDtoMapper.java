package ca.levio.notification.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.levio.messagequeue.messageevent.InterviewAlreadyAcceptedMessageEvent;
import ca.levio.notification.config.LinksConfigProperties;
import ca.levio.notification.dto.TechnicalAdvisorDto;
import ca.levio.notification.maildtos.InterviewAlreadyAcceptedMailDataRequestDto;
import ca.levio.notification.service.TechnicalAdvisorService;

@Mapper(componentModel = "spring")
public abstract class InterviewAlreadyAcceptedMessageEventMailDataDtoMapper {

    @Mapping(target = "linkInterviewDetail", expression = "java(getLinkInterviewDetail(interviewAlreadyAcceptedMessageEvent.getInterview(), linksConfigProperties))")
    @Mapping(target = "to", expression = "java(getTechnicalAdvisorEmail(interviewAlreadyAcceptedMessageEvent.getTechnicalAdvisor(), technicalAdvisorService))")
    @Mapping(target = "technicalAdvisorName", expression = "java(getTechnicalAdvisorName(interviewAlreadyAcceptedMessageEvent.getTechnicalAdvisor(), technicalAdvisorService))")
    public abstract InterviewAlreadyAcceptedMailDataRequestDto interviewAlreadyAcceptedMessageEventToInterviewAlreadyAcceptedMailDataRequestDto(InterviewAlreadyAcceptedMessageEvent interviewAlreadyAcceptedMessageEvent, LinksConfigProperties linksConfigProperties, TechnicalAdvisorService technicalAdvisorService);

    public abstract InterviewAlreadyAcceptedMessageEvent interviewAlreadyAcceptedMailDataRequestDtoToInterviewAlreadyAcceptedMessageEvent(InterviewAlreadyAcceptedMailDataRequestDto interviewAlreadyAcceptedMailDataRequestDto);

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
