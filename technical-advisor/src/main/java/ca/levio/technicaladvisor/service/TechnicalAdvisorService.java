package ca.levio.technicaladvisor.service;

import ca.levio.commonbean.dto.EligibleTechnicalAdvisorDto;
import ca.levio.technicaladvisor.dto.TechnicalAdvisorDto;
import ca.levio.technicaladvisor.enums.LevelOfExpertise;
import ca.levio.technicaladvisor.mapper.TechnicalAdvisorDtoMapper;
import ca.levio.technicaladvisor.model.JobPosition;
import ca.levio.technicaladvisor.model.TechnicalAdvisor;
import ca.levio.technicaladvisor.repository.TechnicalAdvisorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class TechnicalAdvisorService {

    private EligibleTechnicalAdvisorSelector eligibleTechnicalAdvisorSelector;
    private TechnicalAdvisorRepository technicalAdvisorRepository;
    private TechnicalAdvisorDtoMapper technicalAdvisorDtoMapper;
    private JobPositionService jobPositionService;

    public List<TechnicalAdvisor> getTechnicalAdvisors() {
        log.info("get all technical advisors service");
        return technicalAdvisorRepository.findAll();
    }

    public void saveAllTechnicalAdvisors(List<TechnicalAdvisor> technicalAdvisors) {
        technicalAdvisorRepository.saveAllAndFlush(technicalAdvisors);
    }

    public List<EligibleTechnicalAdvisorDto> selectEligibleTechnicalAdvisors(String jobPositionLabel, LevelOfExpertise levelOfExpertise, String interview, Integer numberOfTechnicalAdvisorByBatch) {
        log.info("select technical advisors by criteria service");
        JobPosition jobPosition = jobPositionService.getJobPosition(jobPositionLabel);
        if (jobPosition != null) {
            return eligibleTechnicalAdvisorSelector.selectEligibleTechnicalAdvisorDtos(jobPosition, levelOfExpertise, interview, numberOfTechnicalAdvisorByBatch);
        }
        return new ArrayList<>();
    }

    public TechnicalAdvisor createTechnicalAdvisor(TechnicalAdvisorDto technicalAdvisorDto) {
        log.info("creation technical advisor service {}", technicalAdvisorDto);
        TechnicalAdvisor technicalAdvisor = technicalAdvisorDtoMapper.technicalAdvisorDtoToTechnicalAdvisor(technicalAdvisorDto);
        return technicalAdvisorRepository.saveAndFlush(technicalAdvisor);
    }

    public void deleteAll() {
        technicalAdvisorRepository.deleteAll();
    }

}
