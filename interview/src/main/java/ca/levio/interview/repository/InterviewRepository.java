package ca.levio.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.levio.interview.model.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Integer> {
}
