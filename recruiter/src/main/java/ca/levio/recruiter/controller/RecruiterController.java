package ca.levio.recruiter.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.levio.recruiter.dto.RecruiterDto;

import lombok.AllArgsConstructor;
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/recruiters")
public class RecruiterController {

    @GetMapping("/{id}")
    public ResponseEntity<RecruiterDto> getRecruiter(@PathVariable("id") String id) {
        log.info("get recruiter by id " + id);
        return ResponseEntity.ok().body(new RecruiterDto("ibrahim levio", "ibrahim.boukhenna@gmail.com"));
    }
}
