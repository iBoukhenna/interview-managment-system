package ca.levio.interviewrequest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.levio.interviewrequest.model.InterviewRequest;

public interface InterviewRequestRepository extends JpaRepository<InterviewRequest, Integer> {
}
