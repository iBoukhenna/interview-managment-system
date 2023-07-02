package ca.levio.interviewrequest.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.levio.interviewrequest.service.InterviewRequestService;

import lombok.AllArgsConstructor;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/interviewrequests")
public class InterviewRequestController {

    private final InterviewRequestService interviewRequestService;

    @GetMapping
    public ResponseEntity<?> getAllInterviewRequests() {
        log.info("get all interivew requests");
        return ResponseEntity.ok().body(interviewRequestService.getInterviewRequests());
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<String> acceptInterviewRequest(@PathVariable("id") Long id) {
        String response = "La demande d'entreview avec l'ID " + id + " a été acceptée.";
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<String> rejectInterviewRequest(@PathVariable("id") Long id) {
        String response = "La demande d'entreview avec l'ID " + id + " a été rejetée.";
        return ResponseEntity.ok(response);
    }
}
