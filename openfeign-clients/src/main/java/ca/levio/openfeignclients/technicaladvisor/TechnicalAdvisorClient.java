package ca.levio.openfeignclients.technicaladvisor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "technicalAdvisorClient")
public interface TechnicalAdvisorClient {

    @GetMapping(path = "api/v1/technical-advisors/select-eligible")
    String[] selectEligibleTechnicalAdvisors(
        @RequestParam("jobPosition") String jobPosition,
        @RequestParam("expertiseLevel") String expertiseLevel,
        @RequestParam("x") int x);

}