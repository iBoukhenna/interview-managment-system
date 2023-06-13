package ca.levio.interview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.levio.interview.dto.GenerateInterviewRequestDto;
import ca.levio.interview.model.Interview;

@Mapper(componentModel = "spring")
public abstract class GenerateInterviewRequestDtoMapper {

    @Mapping(target = "interview", expression = "java(interview.getId())")
    @Mapping(target = "x", expression = "java(interview.getTypeOfInterview().getX())")
    public abstract GenerateInterviewRequestDto interviewToInterviewRequestDto(Interview interview);

    @Mapping(target = "id", expression = "java(generateInterviewRequestDto.getInterview())")
    @Mapping(target = "typeOfInterview", expression = "java(ca.levio.interview.enums.TypeOfInterview.getTypeOfInterviewFromX(generateInterviewRequestDto.getX()))")
    public abstract Interview InterviewRequestDtoToInterview(GenerateInterviewRequestDto generateInterviewRequestDto);
}
