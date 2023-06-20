package ca.levio.interview.service;

import ca.levio.commonbean.messageevent.InterviewRequestMessageEvent;
import ca.levio.interview.dto.InterviewDto;
import ca.levio.interview.mapper.InterviewDtoMapper;
import ca.levio.interview.mapper.InterviewToInterviewRequestMessageEventMapper;
import ca.levio.interview.model.Interview;
import ca.levio.interview.queue.MessageQueueProducer;
import ca.levio.interview.repository.InterviewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class InterviewService {

    private InterviewRepository interviewRepository;
    private final MessageQueueProducer<InterviewRequestMessageEvent> messageQueueProducer;
    private final InterviewDtoMapper interviewDtoMapper;
    private InterviewToInterviewRequestMessageEventMapper interviewToInterviewRequestMessageEventMapper;

    public Interview createInterview(InterviewDto interviewDto) {
        log.info("creation interivew service {}", interviewDto);
        Interview interview = interviewDtoMapper.interviewDtoToInterview(interviewDto);
        interview = saveInterview(interview);

        InterviewRequestMessageEvent interviewRequestMessageEvent = interviewToInterviewRequestMessageEventMapper.interviewToInterviewRequestDto(interview);
        messageQueueProducer.send(interviewRequestMessageEvent);
        return interview;
    }

    public Interview saveInterview(Interview interview) {
        log.info("save interivew service {}", interview);
        return interviewRepository.saveAndFlush(interview);
    }

    public List<Interview> getInterviews() {
        log.info("get interivews service {}");
        return interviewRepository.findAll();
    }
}
