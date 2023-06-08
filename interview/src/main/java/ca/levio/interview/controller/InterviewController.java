package ca.levio.interview.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.levio.interview.dto.InterviewDto;
import ca.levio.interview.service.InterviewService;

import lombok.AllArgsConstructor;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/interviews")
public class InterviewController {

    private InterviewService interviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createInterview(@RequestBody InterviewDto interviewCreationRequest) {
        log.info("new customer registration {}", interviewCreationRequest);
        interviewService.createInterview(interviewCreationRequest);
    }
}
