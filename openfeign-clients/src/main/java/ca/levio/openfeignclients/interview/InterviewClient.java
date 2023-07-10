package ca.levio.openfeignclients.interview;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.levio.commonbean.dto.InterviewDto;

@FeignClient(name = "interviewClient")
public interface InterviewClient {

    @GetMapping(path = "api/v1/interviews/{id}")
    InterviewDto getInterview(@PathVariable("id") String id);

}
