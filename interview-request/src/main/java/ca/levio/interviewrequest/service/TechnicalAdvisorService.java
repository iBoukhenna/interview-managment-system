package ca.levio.interviewrequest.service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import ca.levio.openfeignclients.technicaladvisor.TechnicalAdvisorClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class TechnicalAdvisorService {

    private TechnicalAdvisorClient technicalAdvisorClient;

    public List<String> selectEligibleTechnicalAdvisors(String jobPosition, String levelOfExpertise, Integer x) {
        String[] technicalAdvisors = technicalAdvisorClient.selectEligibleTechnicalAdvisors(jobPosition, levelOfExpertise, x);
        
        if (technicalAdvisors != null) {
            return Arrays.asList(technicalAdvisors);
        } else {
            return Collections.emptyList();
        }
    }
}