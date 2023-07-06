package ca.levio.interviewrequest.repository;

import java.util.Optional;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import ca.levio.interviewrequest.enums.StatusOfRequest;
import ca.levio.interviewrequest.model.InterviewRequest;

public interface InterviewRequestRepository extends JpaRepository<InterviewRequest, String> {

    boolean existsByInterviewAndStatusOfRequestIn(String interview, List<StatusOfRequest> statusOfRequests);

    Optional<InterviewRequest> findFirstByInterviewAndStatusOfRequestOrderByAcceptedAtAsc(String interivew, StatusOfRequest statusOfRequest);
}
