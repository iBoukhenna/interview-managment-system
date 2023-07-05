package ca.levio.technicaladvisor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ca.levio.commonbean.dto.EligibleTechnicalAdvisorDto;
import ca.levio.technicaladvisor.enums.LevelOfExpertise;
import ca.levio.technicaladvisor.model.JobPosition;
import ca.levio.technicaladvisor.model.TechnicalAdvisor;
import ca.levio.technicaladvisor.repository.TechnicalAdvisorRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class QueryEligibleTechnicalAdvisorSelector implements EligibleTechnicalAdvisorSelector {

    private TechnicalAdvisorRepository technicalAdvisorRepository;
    private TechnicalAdvisorInterviewService technicalAdvisorInterviewService;

    public List<EligibleTechnicalAdvisorDto> selectEligibleTechnicalAdvisorDtos(JobPosition jobPosition, LevelOfExpertise levelOfExpertise, String interview, Integer numberOfTechnicalAdvisorByBatch) {
        List<TechnicalAdvisor> technicalAdvisors = technicalAdvisorRepository.findEligibleTechnicalAdvisors(jobPosition.getId(), levelOfExpertise.ordinal(), interview, numberOfTechnicalAdvisorByBatch);
        List<EligibleTechnicalAdvisorDto> eligibleTechnicalAdvisors = technicalAdvisors.stream().map(technicalAdvisor -> {
            technicalAdvisorInterviewService.saveTechnicalAdvisorInterview(technicalAdvisor, interview);
            return new EligibleTechnicalAdvisorDto(technicalAdvisor.getId(), technicalAdvisor.getName(), technicalAdvisor.getEmail());
        }).collect(Collectors.toList());
        return eligibleTechnicalAdvisors;
    }
}
