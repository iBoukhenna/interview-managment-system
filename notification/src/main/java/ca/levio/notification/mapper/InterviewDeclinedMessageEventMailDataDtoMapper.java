package ca.levio.notification.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.levio.messagequeue.messageevent.InterviewDeclinedMessageEvent;
import ca.levio.notification.config.LinksConfigProperties;
import ca.levio.notification.dto.TechnicalAdvisorDto;
import ca.levio.notification.maildtos.InterviewDeclinedMailDataRequestDto;
import ca.levio.notification.service.TechnicalAdvisorService;

@Mapper(componentModel = "spring")
public abstract class InterviewDeclinedMessageEventMailDataDtoMapper {

    @Mapping(target = "linkInterviewDetail", expression = "java(getLinkInterviewDetail(interviewDeclinedMessageEvent.getInterview(), linksConfigProperties))")
    @Mapping(target = "to", expression = "java(getTechnicalAdvisorEmail(interviewDeclinedMessageEvent.getTechnicalAdvisor(), technicalAdvisorService))")
    @Mapping(target = "technicalAdvisorName", expression = "java(getTechnicalAdvisorName(interviewDeclinedMessageEvent.getTechnicalAdvisor(), technicalAdvisorService))")
    public abstract InterviewDeclinedMailDataRequestDto interviewDeclinedMessageEventToInterviewDeclinedMailDataRequestDto(InterviewDeclinedMessageEvent interviewDeclinedMessageEvent, LinksConfigProperties linksConfigProperties, TechnicalAdvisorService technicalAdvisorService);

    public abstract InterviewDeclinedMessageEvent interviewDeclinedMailDataRequestDtoToInterviewDeclinedMessageEvent(InterviewDeclinedMailDataRequestDto interviewDeclinedMailDataRequestDto);

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
