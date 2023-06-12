package ca.levio.interview.service;

import ca.levio.interview.dto.InterviewDto;
import ca.levio.interview.enums.StateOfInterview;
import ca.levio.interview.mapper.InterviewMapper;
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
    private final MessageQueueProducer messageQueueProducer;
    private final InterviewMapper createInterviewMapper;

    public Interview createInterview(InterviewDto createInterviewDto) {
        log.info("interivew creation service {}", createInterviewDto);
        Interview interview = createInterviewMapper.interviewDtoToInterview(createInterviewDto);
        interview.setStateOfInterview(StateOfInterview.OPEN);
        interview = interviewRepository.saveAndFlush(interview);
        log.info("send data of interview created {}", interview);
        messageQueueProducer.send(interview);
        return interview;
    }

    public List<Interview> getInterviews() {
        log.info("get all interivews service");
        return interviewRepository.findAll();
    }
}
