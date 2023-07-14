package ca.levio.notification.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.levio.messagequeue.messageevent.NoAvailibleTechnicalAdvisorMessageEvent;
import ca.levio.notification.config.LinksConfigProperties;
import ca.levio.notification.dto.RecruiterDto;
import ca.levio.notification.maildtos.NoAvailibleTechnicalAdvisorMailDataRequestDto;
import ca.levio.notification.service.RecruiterService;

@Mapper(componentModel = "spring")
public abstract class NoAvailibleTechnicalAdvisorMessageEventMailDataDtoMapper {

    @Mapping(target = "linkInterviewDetail", expression = "java(getLinkInterviewDetail(noAvailibleTechnicalAdvisorMessageEvent.getInterview(), linksConfigProperties))")
    @Mapping(target = "to", expression = "java(getRecruiterEmail(noAvailibleTechnicalAdvisorMessageEvent.getRecruiter(), recruiterService))")
    @Mapping(target = "recruiterName", expression = "java(getRecruiterName(noAvailibleTechnicalAdvisorMessageEvent.getRecruiter(), recruiterService))")
    public abstract NoAvailibleTechnicalAdvisorMailDataRequestDto noAvailibleTechnicalAdvisorMessageEventToNoAvailibleTechnicalAdvisorMailDataRequestDto(NoAvailibleTechnicalAdvisorMessageEvent noAvailibleTechnicalAdvisorMessageEvent, LinksConfigProperties linksConfigProperties, RecruiterService recruiterService);

    public abstract NoAvailibleTechnicalAdvisorMessageEvent noAvailibleTechnicalAdvisorMailDataRequestDtoToNoAvailibleTechnicalAdvisorMessageEvent(NoAvailibleTechnicalAdvisorMailDataRequestDto noAvailibleTechnicalAdvisorMailDataRequestDto);

    protected String getLinkInterviewDetail(String interview, LinksConfigProperties linksConfigProperties) {
        return String.format(linksConfigProperties.getInterviewDetailLink(), interview);
    }

    protected String getRecruiterEmail(String recruiter, RecruiterService recruiterService) {
        return getRecruiter(recruiter, recruiterService).getEmail();
    }

    protected String getRecruiterName(String recruiter, RecruiterService recruiterService) {
        return getRecruiter(recruiter, recruiterService).getName();
    }

    private RecruiterDto getRecruiter(String recruiter, RecruiterService recruiterService) {
        return recruiterService.getRecruiter(recruiter);
    }
}
