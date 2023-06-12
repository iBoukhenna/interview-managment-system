package ca.levio.technicaladvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.levio.technicaladvisor.model.TechnicalAdvisor;

public interface TechnicalAdvisorRepository extends JpaRepository<TechnicalAdvisor, Integer> {
}
