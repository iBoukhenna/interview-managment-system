package ca.levio.interviewrequest.service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import ca.levio.commonbean.dto.EligibleTechnicalAdvisorDto;
import ca.levio.openfeignclients.technicaladvisor.TechnicalAdvisorClient;

import java.util.List;

@AllArgsConstructor
@Service
public class TechnicalAdvisorService {

    private final TechnicalAdvisorClient technicalAdvisorClient;

    public List<EligibleTechnicalAdvisorDto> selectEligibleTechnicalAdvisors(String jobPosition, String levelOfExpertise, String interview, Integer numberOfTechnicalAdvisorByBatch) {
        return technicalAdvisorClient.selectEligibleTechnicalAdvisors(jobPosition, levelOfExpertise, interview, numberOfTechnicalAdvisorByBatch);
    }
}