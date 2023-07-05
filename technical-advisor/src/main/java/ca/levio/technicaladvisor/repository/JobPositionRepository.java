package ca.levio.technicaladvisor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.levio.technicaladvisor.model.JobPosition;

public interface JobPositionRepository extends JpaRepository<JobPosition, String> {

    public Optional<JobPosition> findByJobPositionLabel(String jobPositionLabel);
}
