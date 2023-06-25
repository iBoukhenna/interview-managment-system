package ca.levio.interview.mapper;

import org.mapstruct.Mapper;

import ca.levio.interview.dto.InterviewDto;
import ca.levio.interview.model.Interview;

@Mapper(componentModel = "spring")
public abstract class InterviewDtoMapper {

    public abstract InterviewDto interviewToInterviewDto(Interview interview);

    public abstract Interview interviewDtoToInterview(InterviewDto interviewDto);

}
