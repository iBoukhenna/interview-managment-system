package ca.levio.interview.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.levio.interview.dto.InterviewDto;
import ca.levio.interview.model.Interview;
import ca.levio.interview.service.InterviewService;

import lombok.AllArgsConstructor;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping
    public ResponseEntity<?> createInterview(@RequestBody InterviewDto interviewDto) {
        log.info("new interivew creation {}", interviewDto);
        return new ResponseEntity<>(interviewService.createInterview(interviewDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllInterviews() {
        log.info("get all interivews");
        return ResponseEntity.ok().body(interviewService.getInterviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInterview(@PathVariable("id") String id) {
        log.info("get interivew by id " + id);
        Interview interivew = interviewService.getInterview(id);
        if (interivew != null) {
            return ResponseEntity.ok().body(interivew);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
