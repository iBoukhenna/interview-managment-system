package ca.levio.interview.service;

import ca.levio.commonbean.messageevent.InterviewRequestMessageEvent;
import ca.levio.interview.dto.InterviewDto;
import ca.levio.interview.mapper.InterviewDtoMapper;
import ca.levio.interview.mapper.InterviewToInterviewRequestMessageEventMapper;
import ca.levio.interview.model.Interview;
import ca.levio.messagequeue.producer.MessageQueueProducer;
import ca.levio.interview.repository.InterviewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final MessageQueueProducer messageQueueProducer;
    private final InterviewDtoMapper interviewDtoMapper;
    private final InterviewToInterviewRequestMessageEventMapper interviewToInterviewRequestMessageEventMapper;

    public Interview createInterview(InterviewDto interviewDto) {
        log.info("creation interivew service {}", interviewDto);
        Interview interview = interviewDtoMapper.interviewDtoToInterview(interviewDto);
        interview = saveInterview(interview);

        InterviewRequestMessageEvent interviewRequestMessageEvent = interviewToInterviewRequestMessageEventMapper.interviewToInterviewRequestDto(interview);
        //Todo : ton service a trop de responsabilités, tu lui demandes de savoir que le message doit être envoyé sur un topic.
        //Lui il veut juste envoyer un message, là tu lui donnes la possibilités de mettre ce qu'il veut
        //Il y a plusieurs moyens de faire autrement, je te laisse chercher et on en parlera
        messageQueueProducer.send(interviewRequestMessageEvent, InterviewRequestMessageEvent.TOPIC);
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
