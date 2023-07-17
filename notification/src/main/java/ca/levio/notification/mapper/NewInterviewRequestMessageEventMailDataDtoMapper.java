package ca.levio.notification.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.levio.notification.messageevent.NewInterviewRequestMessageEvent;
import ca.levio.notification.config.LinksConfigProperties;
import ca.levio.notification.dto.TechnicalAdvisorDto;
import ca.levio.notification.maildtos.NewInterviewRequestMailDataRequestDto;
import ca.levio.notification.service.TechnicalAdvisorService;

@Mapper(componentModel = "spring")
public abstract class NewInterviewRequestMessageEventMailDataDtoMapper {

    @Mapping(target = "linkAcceptInterview", expression = "java(getLinkAcceptInterview(newInterviewRequestMessageEvent.getInterviewRequest(), linksConfigProperties))")
    @Mapping(target = "linkRejectInterview", expression = "java(getLinkRejectInterview(newInterviewRequestMessageEvent.getInterviewRequest(), linksConfigProperties))")
    @Mapping(target = "linkInterviewDetail", expression = "java(getLinkInterviewDetail(newInterviewRequestMessageEvent.getInterview(), linksConfigProperties))")
    @Mapping(target = "to", expression = "java(getTechnicalAdvisorEmail(newInterviewRequestMessageEvent.getTechnicalAdvisor(), technicalAdvisorService))")
    @Mapping(target = "technicalAdvisorName", expression = "java(getTechnicalAdvisorName(newInterviewRequestMessageEvent.getTechnicalAdvisor(), technicalAdvisorService))")
    public abstract NewInterviewRequestMailDataRequestDto newInterviewRequestMessageEventToNewInterviewRequestMailDataRequestDto(NewInterviewRequestMessageEvent newInterviewRequestMessageEvent, LinksConfigProperties linksConfigProperties, TechnicalAdvisorService technicalAdvisorService);

    public abstract NewInterviewRequestMessageEvent newInterviewRequestMailDataRequestDtoToNewInterviewRequestMessageEvent(NewInterviewRequestMailDataRequestDto newInterviewRequestMailDataRequestDto);

    protected String getLinkAcceptInterview(String interviewRequest, LinksConfigProperties linksConfigProperties) {
        return String.format(linksConfigProperties.getAcceptInterviewRequestLink(), interviewRequest);
    }

    protected String getLinkRejectInterview(String interviewRequest, LinksConfigProperties linksConfigProperties) {
        return String.format(linksConfigProperties.getRejectInterviewRequestLink(), interviewRequest);
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
