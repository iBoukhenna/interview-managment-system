package ca.levio.taskscheduler.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.levio.taskscheduler.model.TechnicalAdvisorSelectionProcess;

public interface TechnicalAdvisorSelectionProcessRepository extends JpaRepository<TechnicalAdvisorSelectionProcess, String> {

    Optional<TechnicalAdvisorSelectionProcess> findByInterview(String interview);
}
