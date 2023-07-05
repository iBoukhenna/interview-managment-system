package ca.levio.technicaladvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.levio.technicaladvisor.model.TechnicalAdvisorInterview;

public interface TechnicalAdvisorInterviewRepository extends JpaRepository<TechnicalAdvisorInterview, String> {
}
