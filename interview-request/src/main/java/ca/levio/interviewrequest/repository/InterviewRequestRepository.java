package ca.levio.interviewrequest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.levio.interviewrequest.enums.StatusOfRequest;
import ca.levio.interviewrequest.model.InterviewRequest;

public interface InterviewRequestRepository extends JpaRepository<InterviewRequest, String> {

    boolean existsByInterviewAndStatusOfRequest(String interview, StatusOfRequest statusOfRequest);

    Optional<InterviewRequest> findFirstByInterviewAndStatusOfRequestOrderByAcceptedAtAsc(String interivew, StatusOfRequest statusOfRequest);
}
