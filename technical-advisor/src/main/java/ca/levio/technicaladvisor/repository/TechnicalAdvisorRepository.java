package ca.levio.technicaladvisor.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ca.levio.technicaladvisor.enums.LevelOfExpertise;
import ca.levio.technicaladvisor.model.TechnicalAdvisor;

public interface TechnicalAdvisorRepository extends JpaRepository<TechnicalAdvisor, String> {
    List<TechnicalAdvisor> findBySkills_JobPositionAndSkills_LevelOfExpertiseGreaterThan(String jobPosition, LevelOfExpertise levelOfExpertise, Pageable pageable);
}
