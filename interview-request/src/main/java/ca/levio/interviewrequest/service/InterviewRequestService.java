package ca.levio.interviewrequest.service;

import ca.levio.interviewrequest.dto.TechnicalAdvisorsDto;
import ca.levio.interviewrequest.enums.StatusOfRequest;
import ca.levio.interviewrequest.model.InterviewRequest;
import ca.levio.interviewrequest.repository.InterviewRequestRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@AllArgsConstructor
@Service
public class InterviewRequestService {

    private InterviewRequestRepository interviewRequestRepository;
    private RestTemplate restTemplate;

    public void createInterviewRequest(InterviewRequest interviewRequest, Integer x, String jobPosition, String levelOfExpertise) {
        log.info("create interivew request service");

        String url = "http://technical-advisor:8083/api/v1/technical-advisors/select?jobPosition=" + jobPosition + "&expertiseLevel=" + levelOfExpertise + "&x=" + x;
        ResponseEntity<String[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                String[].class
        );

        String[] technicalAdvisors = response.getBody();
        InterviewRequest interviewRequestTemp;

        if (technicalAdvisors != null) {
            for (String technicalAdvisor : technicalAdvisors) {
                interviewRequestTemp = new InterviewRequest();
                interviewRequestTemp.setInterview(interviewRequest.getInterview());
                interviewRequestTemp.setTechnicalAdvisor(technicalAdvisor);
                interviewRequestTemp.setStatusOfRequest(StatusOfRequest.OPENED);
                interviewRequestRepository.saveAndFlush(interviewRequestTemp);
            }
        } else {
            log.info("Aucun Technical Advisor trouvé.");
        }
    }

    public List<InterviewRequest> getInterviewRequests() {
        log.info("get all interivew requests service");
        return interviewRequestRepository.findAll();
    }
}
