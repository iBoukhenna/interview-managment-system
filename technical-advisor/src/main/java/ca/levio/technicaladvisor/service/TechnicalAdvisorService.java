package ca.levio.technicaladvisor.service;

import ca.levio.technicaladvisor.enums.LevelOfExpertise;
import ca.levio.technicaladvisor.model.TechnicalAdvisor;
import ca.levio.technicaladvisor.repository.TechnicalAdvisorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class TechnicalAdvisorService {

    private TechnicalAdvisorRepository technicalAdvisorRepository;

    public List<TechnicalAdvisor> getTechnicalAdvisors() {
        log.info("get all technical advisors service");
        return technicalAdvisorRepository.findAll();
    }

    public List<String> selectTechnicalAdvisorsByCriteria(String jobPosition, LevelOfExpertise levelOfExpertise, Integer x) {
        log.info("select technical advisorss by criteria service");
        return new ArrayList<String>() {
            {
                add("ibrahim.boukhenna@levio.ca");
                add("amine.kabouche@levio.ca");
            }
        };
    }
}
