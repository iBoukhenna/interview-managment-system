package ca.levio.interviewrequest.service;

import ca.levio.interviewrequest.model.InterviewRequest;
import ca.levio.interviewrequest.repository.InterviewRequestRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class InterviewRequestService {

    private InterviewRequestRepository interviewRequestRepository;

    public void createInterviewRequest(InterviewRequest interviewRequest) {
        // to do call technical advisor module
        // get technical advisor and save request interview in db
        interviewRequestRepository.saveAndFlush(interviewRequest);
    }
}
