package ca.levio.interviewrequest.openfeign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.levio.interviewrequest.dto.EligibleTechnicalAdvisorDto;

@FeignClient(name = "technicalAdvisorClient")
public interface TechnicalAdvisorClient {

    @GetMapping(path = "api/v1/technical-advisors/select-eligible")
    List<EligibleTechnicalAdvisorDto> selectEligibleTechnicalAdvisors(
        @RequestParam("jobPosition") String jobPosition,
        @RequestParam("expertiseLevel") String expertiseLevel,
        @RequestParam("interview") String interview,
        @RequestParam("numberOfTechnicalAdvisorByBatch") int numberOfTechnicalAdvisorByBatch);

}