package ca.levio.notification.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.levio.commonbean.messageevent.NewInterviewRequestMessageEvent;
import ca.levio.mailmaker.maildtos.NewInterviewRequestMailDataRequestDto;
import ca.levio.notification.config.LinksConfigProperties;

@Mapper(componentModel = "spring")
public abstract class NewInterviewRequestMessageEventMailDataDtoMapper {

    @Mapping(target = "linkAcceptInterview", expression = "java(getLinkAcceptInterview(newInterviewRequestMessageEvent.getInterviewRequest(), linksConfigProperties))")
    @Mapping(target = "linkRejectInterview", expression = "java(getLinkRejectInterview(newInterviewRequestMessageEvent.getInterviewRequest(), linksConfigProperties))")
    @Mapping(target = "linkInterviewDetail", expression = "java(getLinkInterviewDetail(newInterviewRequestMessageEvent.getInterview(), linksConfigProperties))")
    @Mapping(target = "to", source = "newInterviewRequestMessageEvent.technicalAdvisorEmail")
    public abstract NewInterviewRequestMailDataRequestDto newInterviewRequestMessageEventToNewInterviewRequestMailDataRequestDto(NewInterviewRequestMessageEvent newInterviewRequestMessageEvent, LinksConfigProperties linksConfigProperties);

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
}
