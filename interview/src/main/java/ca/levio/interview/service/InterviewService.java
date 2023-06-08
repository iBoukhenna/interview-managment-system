package ca.levio.interview.service;

import ca.levio.interview.dto.InterviewDto;
import ca.levio.interview.mapper.InterviewMapper;
import ca.levio.interview.model.Interview;
import ca.levio.interview.queue.MessageQueueProducer;
import ca.levio.interview.repository.InterviewRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InterviewService {

    private InterviewRepository interviewRepository;
    private final MessageQueueProducer messageQueueProducer;
    private final InterviewMapper createInterviewMapper;

    public void createInterview(InterviewDto createInterviewDto) {
        Interview interview = createInterviewMapper.interviewDtoToInterview(createInterviewDto);
        interview = interviewRepository.saveAndFlush(interview);
        messageQueueProducer.send(interview);
    }

}
