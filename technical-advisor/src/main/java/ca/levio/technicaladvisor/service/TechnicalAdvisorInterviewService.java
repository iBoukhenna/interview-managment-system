package ca.levio.technicaladvisor.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import ca.levio.technicaladvisor.model.TechnicalAdvisor;
import ca.levio.technicaladvisor.model.TechnicalAdvisorInterview;
import ca.levio.technicaladvisor.repository.TechnicalAdvisorInterviewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class TechnicalAdvisorInterviewService {
 
    private TechnicalAdvisorInterviewRepository technicalAdvisorInterviewRepository;

    public void saveTechnicalAdvisorInterview(TechnicalAdvisor technicalAdvisor, String interview) {
        log.info("save technical advisors interview relation");
        technicalAdvisorInterviewRepository.saveAndFlush(new TechnicalAdvisorInterview(UUID.randomUUID().toString(), interview, technicalAdvisor));
    }

    public void deleteAll() {
        technicalAdvisorInterviewRepository.deleteAll();
    }

}
