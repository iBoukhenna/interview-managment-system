package ca.levio.interviewrequest.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.levio.interviewrequest.service.InterviewRequestService;

import lombok.AllArgsConstructor;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/interviewrequests")
public class InterviewRequestController {

    private InterviewRequestService interviewRequestService;

    @GetMapping
    public ResponseEntity<?> getAllInterviewRequests() {
        log.info("get all interivew requests");
        return ResponseEntity.ok().body(interviewRequestService.getInterviewRequests());
    }
}
