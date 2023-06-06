package ca.levio.interview.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.levio.interview.dto.InterviewCreationRequest;
import ca.levio.interview.service.InterviewService;

@Slf4j
@RestController
@RequestMapping("api/v1/interviews")
public record InterviewController(InterviewService interviewService) {


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createInterview(@RequestBody InterviewCreationRequest interviewCreationRequest) {
        log.info("new customer registration {}", interviewCreationRequest);
        interviewService.createInterview(interviewCreationRequest);
    }
}
