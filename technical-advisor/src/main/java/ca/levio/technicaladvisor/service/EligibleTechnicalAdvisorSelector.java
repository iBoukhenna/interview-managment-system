package ca.levio.technicaladvisor.service;

import java.util.List;

import ca.levio.commonbean.dto.EligibleTechnicalAdvisorDto;
import ca.levio.technicaladvisor.enums.LevelOfExpertise;
import ca.levio.technicaladvisor.model.JobPosition;

public interface EligibleTechnicalAdvisorSelector {

    public abstract List<EligibleTechnicalAdvisorDto> selectEligibleTechnicalAdvisorDtos(JobPosition jobPosition, LevelOfExpertise levelOfExpertise, String interview, Integer numberOfTechnicalAdvisorByBatch);
}
