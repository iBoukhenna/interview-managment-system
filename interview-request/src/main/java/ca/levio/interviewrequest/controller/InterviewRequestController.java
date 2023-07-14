package ca.levio.interviewrequest.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.levio.interviewrequest.service.InterviewRequestProcessService;
import ca.levio.interviewrequest.service.InterviewRequestService;

import lombok.AllArgsConstructor;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/interviewrequests")
public class InterviewRequestController {

    private final InterviewRequestProcessService interviewRequestProcessService;
    private final InterviewRequestService interviewRequestService;

    @GetMapping
    public ResponseEntity<?> getAllInterviewRequests() {
        log.info("get all interview requests");
        return ResponseEntity.ok().body(interviewRequestService.getInterviewRequests());
    }

    @GetMapping("/{id}/accept")
    public ResponseEntity<Void> acceptInterviewRequest(@PathVariable("id") String id) {
        log.info("accept interview request {}", id);
        interviewRequestProcessService.acceptInterviewRequestProcess(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/reject")
    public ResponseEntity<Void> rejectInterviewRequest(@PathVariable("id") String id) {
        log.info("reject interview request {}", id);
        interviewRequestProcessService.rejectInterviewRequestProcess(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/decline")
    public ResponseEntity<Void> declineInterviewRequest(@PathVariable("id") String id) {
        log.info("decline interview request {}", id);
        interviewRequestProcessService.declineInterviewRequestProcess(id);
        return ResponseEntity.noContent().build();
    }
}
