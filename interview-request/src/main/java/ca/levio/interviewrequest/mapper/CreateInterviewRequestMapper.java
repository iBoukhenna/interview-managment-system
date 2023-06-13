package ca.levio.interviewrequest.mapper;

import org.mapstruct.Mapper;

import ca.levio.interview.dto.GenerateInterviewRequestDto;
import ca.levio.interviewrequest.model.InterviewRequest;

@Mapper(componentModel = "spring")
public abstract class CreateInterviewRequestMapper {

    public abstract GenerateInterviewRequestDto interviewRequestToInterviewRequestDto(InterviewRequest interviewRequest);

    public abstract InterviewRequest interviewRequestDtoToInterviewRequest(GenerateInterviewRequestDto generateInterviewRequestDto);

}
