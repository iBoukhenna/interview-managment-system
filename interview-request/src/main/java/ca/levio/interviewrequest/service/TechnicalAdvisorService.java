package ca.levio.interviewrequest.service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import ca.levio.commonbean.dto.EligibleTechnicalAdvisorDto;
import ca.levio.openfeignclients.technicaladvisor.TechnicalAdvisorClient;

import java.util.List;

@AllArgsConstructor
@Service
public class TechnicalAdvisorService {

    private TechnicalAdvisorClient technicalAdvisorClient;

    public List<EligibleTechnicalAdvisorDto> selectEligibleTechnicalAdvisors(String jobPosition, String levelOfExpertise, Integer x) {
        return technicalAdvisorClient.selectEligibleTechnicalAdvisors(jobPosition, levelOfExpertise, x);
    }
}