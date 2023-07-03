package ca.levio.interview.service;

import ca.levio.commonbean.messageevent.NewInterviewMessageEvent;
import ca.levio.interview.dto.InterviewDto;
import ca.levio.interview.mapper.InterviewDtoMapper;
import ca.levio.interview.mapper.InterviewNewInterviewMessageEventMapper;
import ca.levio.interview.model.Interview;
import ca.levio.messagequeue.producer.MessageQueueProducer;
import ca.levio.interview.repository.InterviewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final MessageQueueProducer messageQueueProducer;
    private final InterviewDtoMapper interviewDtoMapper;
    private final InterviewNewInterviewMessageEventMapper interviewNewInterviewMessageEventMapper;

    public Interview createInterview(InterviewDto interviewDto) {
        log.info("creation interivew service {}", interviewDto);
        Interview interview = interviewDtoMapper.interviewDtoToInterview(interviewDto);
        interview = saveInterview(interview);
        sendMessageEventToInterviewRequest(interview);
        return interview;
    }

    public void sendMessageEventToInterviewRequest(Interview interview) {
        NewInterviewMessageEvent newInterviewMessageEvent = interviewNewInterviewMessageEventMapper.interviewToNewInterviewMessageEvent(interview);
        messageQueueProducer.send(newInterviewMessageEvent);
    }

    public Interview saveInterview(Interview interview) {
        log.info("save interivew service {}", interview);
        return interviewRepository.saveAndFlush(interview);
    }

    public List<Interview> getInterviews() {
        log.info("get interivews service");
        return interviewRepository.findAll();
    }

    public Interview getInterview(String id) {
        log.info("get interivew service {}", id);
        Optional<Interview> optionalInterview = interviewRepository.findById(id);
        return optionalInterview.isPresent() ? optionalInterview.get() : null;
    }
}
