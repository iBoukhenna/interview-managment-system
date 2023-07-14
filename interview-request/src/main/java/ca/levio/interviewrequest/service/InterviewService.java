package ca.levio.interviewrequest.service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import ca.levio.interviewrequest.dto.InterviewDto;
import ca.levio.interviewrequest.openfeign.InterviewClient;

@AllArgsConstructor
@Service
public class InterviewService {

    private final InterviewClient interviewClient;

    public InterviewDto getInterview(String interview) {
        return interviewClient.getInterview(interview).getBody();
    }
}