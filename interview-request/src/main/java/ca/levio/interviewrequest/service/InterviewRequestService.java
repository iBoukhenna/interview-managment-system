package ca.levio.interviewrequest.service;

import ca.levio.interviewrequest.dto.InterviewRequestCreationRequest;
import ca.levio.interviewrequest.repository.InterviewRequestRepository;

import org.springframework.stereotype.Service;

@Service
public record InterviewRequestService(InterviewRequestRepository interviewRequestRepository) {

    public void createInterviewRequest(InterviewRequestCreationRequest request) {
        // to do call technical advisor module
        // get technical advisor and save request interview in db
    }
}
