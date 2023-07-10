package ca.levio.interview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.levio.commonbean.dto.InterviewDto;
import ca.levio.interview.model.Interview;

@Mapper(componentModel = "spring")
public abstract class InterviewDtoMapper {

    @Mapping(target = "numberOfTechnicalAdvisorByBatch", source = "interview.typeOfInterview.numberOfTechnicalAdvisorByBatch")
    @Mapping(target = "delayBeforeRetrying", source = "interview.typeOfInterview.delayBeforeRetrying")
    public abstract InterviewDto interviewToInterviewDto(Interview interview);

    public abstract Interview interviewDtoToInterview(InterviewDto interviewDto);

}
