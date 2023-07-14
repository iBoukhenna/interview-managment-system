package ca.levio.interviewrequest.service;

import ca.levio.interviewrequest.dto.InterviewDto;
import ca.levio.interviewrequest.model.TechnicalAdvisorSelectionProcess;
import ca.levio.interviewrequest.repository.TechnicalAdvisorSelectionProcessRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TechnicalAdvisorSelectionProcessService {

    private final TechnicalAdvisorSelectionProcessRepository technicalAdvisorSelectionProcessRepository;

    public Collection<TechnicalAdvisorSelectionProcess> selectAllTechnicalAdvisorSelectionProcess() {
        return technicalAdvisorSelectionProcessRepository.findAll();
    }

    public void deleteTechnicalAdvisorSelectionProcess(String interview) {
        TechnicalAdvisorSelectionProcess technicalAdvisorSelectionProcess = getTechnicalAdvisorSelectionProcessByInterview(interview);
        if (technicalAdvisorSelectionProcess == null) return;

        technicalAdvisorSelectionProcessRepository.delete(technicalAdvisorSelectionProcess);
    }

    public TechnicalAdvisorSelectionProcess getTechnicalAdvisorSelectionProcessByInterview(String interview) {
        Optional<TechnicalAdvisorSelectionProcess> optionalLastSelectionProcess = technicalAdvisorSelectionProcessRepository.findByInterview(interview);
        return optionalLastSelectionProcess.isPresent() ? optionalLastSelectionProcess.get() : null;
    }

    public TechnicalAdvisorSelectionProcess getTechnicalAdvisorSelectionProcessByInterviewDto(InterviewDto interviewDto) {
        TechnicalAdvisorSelectionProcess technicalAdvisorSelectionProcess = getTechnicalAdvisorSelectionProcessByInterview(interviewDto.getInterview());
        if (technicalAdvisorSelectionProcess != null) {
            return updateLastDateProcessForTechnicalAdvisorSelectionProcess(technicalAdvisorSelectionProcess);
        } else {
            return createTechnicalAdvisorSelectionProcess(interviewDto.getInterview(), interviewDto.getDelayBeforeRetrying());
        }
    }

    private TechnicalAdvisorSelectionProcess createTechnicalAdvisorSelectionProcess(String interview, int delayBeforeRetrying) {
        TechnicalAdvisorSelectionProcess technicalAdvisorSelectionProcess = TechnicalAdvisorSelectionProcess.builder()
            .interview(interview).delayBeforeRetrying(delayBeforeRetrying).lastDateProcess(LocalDateTime.now()).build();
        return technicalAdvisorSelectionProcessRepository.save(technicalAdvisorSelectionProcess);
    }

    private TechnicalAdvisorSelectionProcess updateLastDateProcessForTechnicalAdvisorSelectionProcess(TechnicalAdvisorSelectionProcess technicalAdvisorSelectionProcess) {
        technicalAdvisorSelectionProcess.setLastDateProcess(LocalDateTime.now());
        return technicalAdvisorSelectionProcessRepository.save(technicalAdvisorSelectionProcess);
    }

    public LocalDateTime selectNextRunProcessTime(TechnicalAdvisorSelectionProcess technicalAdvisorSelectionProcess) {
        return technicalAdvisorSelectionProcess.getLastDateProcess().plusMinutes(technicalAdvisorSelectionProcess.getDelayBeforeRetrying());
    }
}
