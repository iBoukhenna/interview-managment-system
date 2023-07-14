package ca.levio.technicaladvisor.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.levio.technicaladvisor.dto.EligibleTechnicalAdvisorDto;
import ca.levio.technicaladvisor.dto.TechnicalAdvisorDto;
import ca.levio.technicaladvisor.enums.LevelOfExpertise;
import ca.levio.technicaladvisor.service.TechnicalAdvisorService;

import lombok.AllArgsConstructor;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/technical-advisors")
public class TechnicalAdvisorController {

    private TechnicalAdvisorService technicalAdvisorService;

    @PostMapping
    public ResponseEntity<TechnicalAdvisorDto> createTechnicalAdvisor(@RequestBody TechnicalAdvisorDto technicalAdvisorDto) {
        log.info("new technical advisor creation {}", technicalAdvisorDto);
        return new ResponseEntity<TechnicalAdvisorDto>(technicalAdvisorService.createTechnicalAdvisor(technicalAdvisorDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TechnicalAdvisorDto>> getAllTechnicalAdvisors() {
        log.info("get all technical advisors");
        return ResponseEntity.ok().body(technicalAdvisorService.getTechnicalAdvisors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicalAdvisorDto> getInterview(@PathVariable("id") String id) {
        log.info("get interview by id " + id);
        return ResponseEntity.ok().body(technicalAdvisorService.getTechnicalAdvisorById(id));
    }

    @GetMapping("/select-eligible")
    public ResponseEntity<List<EligibleTechnicalAdvisorDto>> selectEligibleTechnicalAdvisors(
        @RequestParam("jobPosition") String jobPosition,
        @RequestParam("expertiseLevel") LevelOfExpertise expertiseLevel,
        @RequestParam("interview") String interview,
        @RequestParam("numberOfTechnicalAdvisorByBatch") int numberOfTechnicalAdvisorByBatch) {

        log.info("select technical advisors");
        try {
            List<EligibleTechnicalAdvisorDto> technicalAdvisors = technicalAdvisorService.selectEligibleTechnicalAdvisors(jobPosition, expertiseLevel, interview, numberOfTechnicalAdvisorByBatch);
            if (technicalAdvisors.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(technicalAdvisors);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
