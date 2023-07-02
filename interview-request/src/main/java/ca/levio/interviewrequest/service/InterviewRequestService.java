package ca.levio.interviewrequest.service;

import ca.levio.commonbean.dto.EligibleTechnicalAdvisorDto;
import ca.levio.commonbean.messageevent.NewInterviewRequestMessageEvent;
import ca.levio.interviewrequest.model.InterviewRequest;
import ca.levio.interviewrequest.repository.InterviewRequestRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import ca.levio.messagequeue.producer.MessageQueueProducer;

import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class InterviewRequestService {

    private final InterviewRequestRepository interviewRequestRepository;
    private final TechnicalAdvisorService technicalAdvisorService;
    private final MessageQueueProducer messageQueueProducer;

    public void createInterviewRequest(InterviewRequest interviewRequest, Integer x, String jobPosition, String levelOfExpertise) {
        log.info("create interview request service");

        List<EligibleTechnicalAdvisorDto> eligibleTechnicalAdvisorDtos = technicalAdvisorService.selectEligibleTechnicalAdvisors(jobPosition, levelOfExpertise, x);

        if (eligibleTechnicalAdvisorDtos != null) {
            eligibleTechnicalAdvisorDtos.forEach(eligibleTechnicalAdvisorDto -> {
                InterviewRequest interviewRequestTemp = InterviewRequest.builder()
                        .interview(interviewRequest.getInterview())
                        .technicalAdvisor(eligibleTechnicalAdvisorDto.getId())
                        .build();
                interviewRequestTemp = interviewRequestRepository.saveAndFlush(interviewRequestTemp);
                NewInterviewRequestMessageEvent newInterviewRequestMessageEvent = new NewInterviewRequestMessageEvent(eligibleTechnicalAdvisorDto.getEmail(), eligibleTechnicalAdvisorDto.getName(), interviewRequest.getInterview(), interviewRequestTemp.getId());
                messageQueueProducer.send(newInterviewRequestMessageEvent);
            });
        } else {
            log.info("Aucun Technical Advisor trouvé.");
        }
    }

    public List<InterviewRequest> getInterviewRequests() {
        log.info("get all interivew requests service");
        return interviewRequestRepository.findAll();
    }
}
