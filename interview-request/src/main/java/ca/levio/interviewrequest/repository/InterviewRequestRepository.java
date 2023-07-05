package ca.levio.interviewrequest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.levio.interviewrequest.enums.StatusOfRequest;
import ca.levio.interviewrequest.model.InterviewRequest;

public interface InterviewRequestRepository extends JpaRepository<InterviewRequest, String> {

    boolean existsByInterviewAndStatusOfRequestAndStatusOfRequestOrStatusOfRequest(String interview, StatusOfRequest statusOfRequest1, StatusOfRequest statusOfRequest2);

    Optional<InterviewRequest> findFirstByInterviewAndStatusOfRequestOrderByAcceptedAtAsc(String interivew, StatusOfRequest statusOfRequest);
}
