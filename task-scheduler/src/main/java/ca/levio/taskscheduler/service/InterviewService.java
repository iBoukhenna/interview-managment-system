package ca.levio.taskscheduler.service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import ca.levio.commonbean.dto.InterviewDto;
import ca.levio.openfeignclients.interview.InterviewClient;

@AllArgsConstructor
@Service
public class InterviewService {

    private final InterviewClient interviewClient;

    public InterviewDto getInterview(String interview) {
        return interviewClient.getInterview(interview);
    }
}