package ca.levio.interview.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.levio.interview.dto.InterviewDto;
import ca.levio.interview.service.InterviewService;

import lombok.AllArgsConstructor;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping
    public ResponseEntity<InterviewDto> createInterview(@RequestBody InterviewDto interviewDto) {
        log.info("new interview creation {}", interviewDto);
        return new ResponseEntity<InterviewDto>(interviewService.createInterview(interviewDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InterviewDto>> getAllInterviews() {
        log.info("get all interviews");
        return ResponseEntity.ok().body(interviewService.getInterviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterviewDto> getInterview(@PathVariable("id") String id) {
        log.info("get interview by id " + id);
        return ResponseEntity.ok().body(interviewService.getInterview(id));
    }
}
