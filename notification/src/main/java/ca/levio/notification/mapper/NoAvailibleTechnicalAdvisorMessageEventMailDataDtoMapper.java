package ca.levio.notification.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.levio.commonbean.messageevent.NoAvailibleTechnicalAdvisorMessageEvent;
import ca.levio.mailmaker.maildtos.NoAvailibleTechnicalAdvisorMailDataRequestDto;
import ca.levio.notification.config.LinksConfigProperties;

@Mapper(componentModel = "spring")
public abstract class NoAvailibleTechnicalAdvisorMessageEventMailDataDtoMapper {

    @Mapping(target = "linkInterviewDetail", expression = "java(getLinkInterviewDetail(noAvailibleTechnicalAdvisorMessageEvent.getInterview(), linksConfigProperties))")
    @Mapping(target = "to", source = "noAvailibleTechnicalAdvisorMessageEvent.recruiterEmail")
    public abstract NoAvailibleTechnicalAdvisorMailDataRequestDto noAvailibleTechnicalAdvisorMessageEventToNoAvailibleTechnicalAdvisorMailDataRequestDto(NoAvailibleTechnicalAdvisorMessageEvent noAvailibleTechnicalAdvisorMessageEvent, LinksConfigProperties linksConfigProperties);

    public abstract NoAvailibleTechnicalAdvisorMessageEvent noAvailibleTechnicalAdvisorMailDataRequestDtoToNoAvailibleTechnicalAdvisorMessageEvent(NoAvailibleTechnicalAdvisorMailDataRequestDto noAvailibleTechnicalAdvisorMailDataRequestDto);

    protected String getLinkInterviewDetail(String interview, LinksConfigProperties linksConfigProperties) {
        return String.format(linksConfigProperties.getInterviewDetailLink(), interview);
    }
}
