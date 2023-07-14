package ca.levio.interviewrequest.service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import ca.levio.interviewrequest.dto.EligibleTechnicalAdvisorDto;
import ca.levio.interviewrequest.openfeign.TechnicalAdvisorClient;

import java.util.List;

@AllArgsConstructor
@Service
public class TechnicalAdvisorService {

    private final TechnicalAdvisorClient technicalAdvisorClient;

    public List<EligibleTechnicalAdvisorDto> selectEligibleTechnicalAdvisors(String jobPosition, String levelOfExpertise, String interview, Integer numberOfTechnicalAdvisorByBatch) {
        return technicalAdvisorClient.selectEligibleTechnicalAdvisors(jobPosition, levelOfExpertise, interview, numberOfTechnicalAdvisorByBatch);
    }
}