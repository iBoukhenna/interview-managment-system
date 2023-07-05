package ca.levio.technicaladvisor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ca.levio.technicaladvisor.model.JobPosition;
import ca.levio.technicaladvisor.repository.JobPositionRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JobPositionService {

    private final JobPositionRepository jobPositionRepository;

    public JobPosition getJobPosition(String jobPosition) {
        Optional<JobPosition> optionalJobPosition = jobPositionRepository.findByJobPositionLabel(jobPosition);
        return optionalJobPosition.isPresent() ? optionalJobPosition.get() : null;
    }

    public void saveAllJobPositions(List<JobPosition> jobPositions) {
        jobPositionRepository.saveAllAndFlush(jobPositions);
    }

    public void deleteAll() {
        jobPositionRepository.deleteAll();
    }
}
