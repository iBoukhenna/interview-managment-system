package ca.levio.technicaladvisor.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.levio.commonbean.dto.EligibleTechnicalAdvisorDto;
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
    public ResponseEntity<?> createTechnicalAdvisor(@RequestBody TechnicalAdvisorDto technicalAdvisorDto) {
        log.info("new technical advisor creation {}", technicalAdvisorDto);
        return new ResponseEntity<>(technicalAdvisorService.createTechnicalAdvisor(technicalAdvisorDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllTechnicalAdvisors() {
        log.info("get all technical advisors");
        return ResponseEntity.ok().body(technicalAdvisorService.getTechnicalAdvisors());
    }

    @GetMapping("/select-eligible")
    public ResponseEntity<?> selectEligibleTechnicalAdvisors(
        @RequestParam("jobPosition") String jobPosition,
        @RequestParam("expertiseLevel") LevelOfExpertise expertiseLevel,
        @RequestParam("x") int x) {

        log.info("select technical advisors");
        try {
            List<EligibleTechnicalAdvisorDto> technicalAdvisors = technicalAdvisorService.selectTechnicalAdvisorsByCriteria(jobPosition, expertiseLevel, x);
            if (technicalAdvisors.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(technicalAdvisors);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors de la récupération des Technical Advisors.");
        }
    }
}
