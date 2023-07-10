package ca.levio.interviewrequest.repository;

import java.util.Optional;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ca.levio.interviewrequest.enums.StatusOfRequest;
import ca.levio.interviewrequest.model.InterviewRequest;

public interface InterviewRequestRepository extends JpaRepository<InterviewRequest, String> {

    @Query("SELECT COUNT(ir) = COUNT(ir) FILTER (WHERE ir.statusOfRequest = 'REJECTED') FROM InterviewRequest ir WHERE ir.interview = :interview")
    boolean areAllInterviewRequestsRejected(@Param("interview") String interview);

    boolean existsByInterviewAndStatusOfRequestIn(String interview, List<StatusOfRequest> statusOfRequests);

    Optional<InterviewRequest> findFirstByInterviewAndStatusOfRequestOrderByAcceptedAtAsc(String interivew, StatusOfRequest statusOfRequest);
}
