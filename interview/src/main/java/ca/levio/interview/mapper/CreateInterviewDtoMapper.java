package ca.levio.interview.mapper;

import org.mapstruct.Mapper;

import ca.levio.interview.dto.CreateInterviewDto;
import ca.levio.interview.model.Interview;

@Mapper(componentModel = "spring")
public abstract class CreateInterviewDtoMapper {

    public abstract CreateInterviewDto interviewToInterviewDto(Interview interview);

    public abstract Interview interviewDtoToInterview(CreateInterviewDto createInterviewDto);

}
