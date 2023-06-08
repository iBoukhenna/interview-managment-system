package ca.levio.interviewrequest.mapper;

import org.mapstruct.Mapper;

import ca.levio.interview.dto.InterviewRequestDto;
import ca.levio.interviewrequest.model.InterviewRequest;

@Mapper(componentModel = "spring")
public abstract class InterviewRequestMapper {

    public abstract InterviewRequestDto interviewRequestToInterviewRequestDto(InterviewRequest interviewRequest);

    public abstract InterviewRequest interviewRequestDtoToInterviewRequest(InterviewRequestDto interviewRequestDto);

}
