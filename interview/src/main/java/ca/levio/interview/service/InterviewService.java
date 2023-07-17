package ca.levio.interview.service;

import ca.levio.interview.dto.InterviewDto;
import ca.levio.interview.enums.StateOfInterview;
import ca.levio.interview.mapper.InterviewDtoMapper;
import ca.levio.interview.mapper.InterviewNewInterviewMessageEventMapper;
import ca.levio.interview.model.Interview;
import ca.levio.interview.messageevent.NewInterviewMessageEvent;
import ca.levio.interview.producer.MessageQueueProducer;
import ca.levio.interview.repository.InterviewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final MessageQueueProducer messageQueueProducer;
    private final InterviewDtoMapper interviewDtoMapper;
    private final InterviewNewInterviewMessageEventMapper interviewNewInterviewMessageEventMapper;

    public InterviewDto createInterview(InterviewDto interviewDto) {
        log.info("creation interview service {}", interviewDto);
        Interview interview = interviewDtoMapper.interviewDtoToInterview(interviewDto);
        interview = saveInterview(interview);
        sendNewInterviewMessageEvent(interview);
        return interviewDtoMapper.interviewToInterviewDto(interview);
    }

    public void sendNewInterviewMessageEvent(Interview interview) {
        NewInterviewMessageEvent newInterviewMessageEvent = interviewNewInterviewMessageEventMapper.interviewToNewInterviewMessageEvent(interview);
        messageQueueProducer.send(newInterviewMessageEvent);
    }

    public Interview saveInterview(Interview interview) {
        log.info("save interview service {}", interview);
        return interviewRepository.saveAndFlush(interview);
    }

    public List<InterviewDto> getInterviews() {
        log.info("get interviews service");
        List<Interview> interviews = interviewRepository.findAll();
        return interviews.stream().map(interviewDtoMapper::interviewToInterviewDto).collect(Collectors.toList());
    }

    public InterviewDto getInterview(String id) {
        log.info("get interview service {}", id);
        Optional<Interview> optionalInterview = interviewRepository.findById(id);
        return optionalInterview.isPresent() ? interviewDtoMapper.interviewToInterviewDto(optionalInterview.get()) : null;
    }

    public void updateInterviewNoAvailibleTechnicalAdvisor(String interviewId) {
        Optional<Interview> interviewOptional = interviewRepository.findById(interviewId);
        if (interviewOptional.isPresent()) {
            Interview interview = interviewOptional.get();
            interview.setStateOfInterview(StateOfInterview.INTERRUPTED);
            interviewRepository.save(interview);
        }
    }
}
