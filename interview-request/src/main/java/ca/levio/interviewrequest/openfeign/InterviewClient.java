package ca.levio.interviewrequest.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.levio.interviewrequest.dto.InterviewDto;

@FeignClient(name = "interviewClient")
public interface InterviewClient {

    @GetMapping(path = "api/v1/interviews/{id}")
    ResponseEntity<InterviewDto> getInterview(@PathVariable("id") String id);
}
