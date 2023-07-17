package ca.levio.notification.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.levio.notification.messageevent.InterviewAssignedMessageEvent;
import ca.levio.notification.config.LinksConfigProperties;
import ca.levio.notification.dto.RecruiterDto;
import ca.levio.notification.maildtos.InterviewAssignedMailDataRequestDto;
import ca.levio.notification.service.RecruiterService;

@Mapper(componentModel = "spring")
public abstract class InterviewAssignedMessageEventMailDataDtoMapper {

    @Mapping(target = "linkTechnicalAdvisorDetail", expression = "java(getLinkTechnicalAdvisorDetail(interviewAssignedMessageEvent.getTechnicalAdvisor(), linksConfigProperties))")
    @Mapping(target = "linkInterviewDetail", expression = "java(getLinkInterviewDetail(interviewAssignedMessageEvent.getInterview(), linksConfigProperties))")
    @Mapping(target = "to", expression = "java(getRecruiterEmail(interviewAssignedMessageEvent.getRecruiter(), recruiterService))")
    @Mapping(target = "recruiterName", expression = "java(getRecruiterName(interviewAssignedMessageEvent.getRecruiter(), recruiterService))")
    public abstract InterviewAssignedMailDataRequestDto interviewAssignedMessageEventToInterviewAssignedMailDataRequestDto(InterviewAssignedMessageEvent interviewAssignedMessageEvent, LinksConfigProperties linksConfigProperties, RecruiterService recruiterService);

    public abstract InterviewAssignedMessageEvent interviewAssignedMailDataRequestDtoToInterviewAssignedMessageEvent(InterviewAssignedMailDataRequestDto interviewAssignedMailDataRequestDto);

    protected String getLinkTechnicalAdvisorDetail(String technicalAdvisor, LinksConfigProperties linksConfigProperties) {
        return String.format(linksConfigProperties.getTechnicalAdvisorDetailLink(), technicalAdvisor);
    }

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
