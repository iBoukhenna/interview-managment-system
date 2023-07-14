package ca.levio.interviewrequest.service;

import ca.levio.interviewrequest.enums.StatusOfRequest;
import ca.levio.interviewrequest.model.InterviewRequest;
import ca.levio.interviewrequest.repository.InterviewRequestRepository;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class InterviewRequestService {

    private final InterviewRequestRepository interviewRequestRepository;
    private final MessageQueueProducerService messageQueueProducerService;

    public List<InterviewRequest> getInterviewRequests() {
        return interviewRequestRepository.findAll();
    }

    public InterviewRequest getInterviewRequestById(String id) {
        Optional<InterviewRequest> optionalInterviewRequest = interviewRequestRepository.findById(id);
        return optionalInterviewRequest.isPresent() ? optionalInterviewRequest.get() : null;
    }

    protected InterviewRequest createInterviewRequest(InterviewRequest interviewRequest) {
        return interviewRequestRepository.saveAndFlush(interviewRequest);
    }


    public void accepteInterviewRequest(InterviewRequest interviewRequest) {
        updateInterviewRequestStatusAndAcceptedAt(interviewRequest, StatusOfRequest.ACCEPTED);
        messageQueueProducerService.sendInterviewAlreadyAcceptedMessageEvent(interviewRequest);
    }

    public void rejectInterviewRequest(InterviewRequest interviewRequest) {
        updateInterviewRequestStatus(interviewRequest, StatusOfRequest.REJECTED_BY_TECHNICAL_ADVISOR);
    }

    public void assigneInterviewRequest(InterviewRequest interviewRequest) {
        updateInterviewRequestStatusAndAcceptedAt(interviewRequest, StatusOfRequest.ASSIGNED);
        messageQueueProducerService.sendInterviewAcceptedMessageEvent(interviewRequest);
        messageQueueProducerService.sendInterviewAssignedMessageEvent(interviewRequest);
    }

    public void declineInterviewRequest(InterviewRequest interviewRequest) {
        updateInterviewRequestStatus(interviewRequest, StatusOfRequest.DECLINED_BY_RECRUITER);
        messageQueueProducerService.sendInterviewDeclinedMessageEvent(interviewRequest);
    }

    private void updateInterviewRequestStatusAndAcceptedAt(InterviewRequest interviewRequest, StatusOfRequest statusOfRequest) {
        interviewRequest.setAcceptedAt(LocalDateTime.now());
        updateInterviewRequestStatus(interviewRequest, statusOfRequest);
    }

    private void updateInterviewRequestStatus(InterviewRequest interviewRequest, StatusOfRequest statusOfRequest) {
        interviewRequest.setStatusOfRequest(statusOfRequest);
        interviewRequestRepository.save(interviewRequest);
    }

    public InterviewRequest getInterviewRequestAccepetedByInterview(String interview) {
        Optional<InterviewRequest> optionalInterviewRequest = interviewRequestRepository.findFirstByInterviewAndStatusOfRequestOrderByAcceptedAtAsc(interview, StatusOfRequest.ACCEPTED);
        return optionalInterviewRequest.isPresent() ? optionalInterviewRequest.get() : null;
    }

    public boolean checkIfInterviewIsAlreadyAccepted(String interview) {
        return interviewRequestRepository.existsByInterviewAndStatusOfRequestIn(interview, Arrays.asList(StatusOfRequest.ACCEPTED, StatusOfRequest.ASSIGNED));
    }

    public boolean checkIfAllInterviewRequestRejected(InterviewRequest interviewRequest) {
        return interviewRequestRepository.areAllInterviewRequestsRejectedByTechnicalAdvisor(interviewRequest.getInterview(), StatusOfRequest.REJECTED_BY_TECHNICAL_ADVISOR);
    }
}
