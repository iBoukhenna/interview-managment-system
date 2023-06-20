package ca.levio.technicaladvisor.service;

import ca.levio.commonbean.dto.EligibleTechnicalAdvisorDto;
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

    public List<EligibleTechnicalAdvisorDto> selectTechnicalAdvisorsByCriteria(String jobPosition, LevelOfExpertise levelOfExpertise, Integer x) {
        log.info("select technical advisorss by criteria service");
        return new ArrayList<EligibleTechnicalAdvisorDto>() {
            {
                add(new EligibleTechnicalAdvisorDto("1", "a", "a@gmail.com"));
                add(new EligibleTechnicalAdvisorDto("2", "b", "b@gmail.com"));
            }
        };
    }
}
