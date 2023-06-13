package ca.levio.interviewrequest.service;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class TechnicalAdvisorService {

    private RestTemplate restTemplate;

    private ExternalService externalService;


    public List<String> getTechnicalAdvisors(String jobPosition, String levelOfExpertise, Integer x) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(externalService.returnTechnicalServiceUrl())
            .queryParam("jobPosition", jobPosition)
            .queryParam("expertiseLevel", levelOfExpertise)
            .queryParam("x", x);

        String url = builder.toUriString();

        ResponseEntity<String[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                String[].class
        );

        String[] technicalAdvisors = response.getBody();
        if (technicalAdvisors != null) {
            return Arrays.asList(technicalAdvisors);
        } else {
            return Collections.emptyList();
        }
    }
}