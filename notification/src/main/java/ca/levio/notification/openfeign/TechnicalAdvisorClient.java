package ca.levio.notification.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.levio.notification.dto.TechnicalAdvisorDto;

@FeignClient(name = "technicalAdvisorClient")
public interface TechnicalAdvisorClient {

    @GetMapping(path = "api/v1/technical-advisors/{id}")
    ResponseEntity<TechnicalAdvisorDto> getTechnicalAdvisor(@PathVariable("id") String id);

}