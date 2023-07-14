package ca.levio.notification.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.levio.notification.dto.RecruiterDto;

@FeignClient(name = "recruiterClient")
public interface RecruiterClient {

    @GetMapping(path = "api/v1/recruiters/{id}")
    ResponseEntity<RecruiterDto> getRecruiter(@PathVariable("id") String id);
}
