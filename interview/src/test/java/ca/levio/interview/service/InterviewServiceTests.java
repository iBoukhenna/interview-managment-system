package ca.levio.interview.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import ca.levio.commonbean.messageevent.InterviewRequestMessageEvent;
import ca.levio.interview.dto.InterviewDto;
import ca.levio.interview.enums.LevelOfExpertise;
import ca.levio.interview.enums.StateOfInterview;
import ca.levio.interview.enums.TypeOfInterview;
import ca.levio.interview.mapper.InterviewDtoMapper;
import ca.levio.interview.mapper.InterviewToInterviewRequestMessageEventMapper;
import ca.levio.interview.model.Interview;
import ca.levio.messagequeue.producer.MessageQueueProducer;
import ca.levio.interview.repository.InterviewRepository;

import java.util.UUID;

@SpringBootTest
public class InterviewServiceTests {

    @Mock
    private InterviewRepository interviewRepository;

    @Mock
    private MessageQueueProducer messageQueueProducer;

    @Mock
    private InterviewDtoMapper interviewDtoMapper;

    @Mock
    private InterviewToInterviewRequestMessageEventMapper interviewRequestMessageEventMapper;

    @InjectMocks
    private InterviewService interviewService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createInterviewTest() {
        InterviewDto interviewDto = new InterviewDto("Ahmed@gmail.com"
            , "Amine@levio.ca", "Java Developer"
            , LevelOfExpertise.INTERMEDIATE.getCode(), TypeOfInterview.NOT_URGENT.getCode());

        Interview interviewExpected = interviewDtoMapper.interviewDtoToInterview(interviewDto);

        Mockito.doNothing().when(messageQueueProducer).send(any(InterviewRequestMessageEvent.class), InterviewRequestMessageEvent.TOPIC);
        when(interviewRepository.saveAndFlush(any(Interview.class))).thenReturn(interviewExpected);

        Interview interviewActual = interviewService.createInterview(interviewDto);
        assertEquals(interviewExpected, interviewActual);
    }

    @Test
    void saveInterviewTest() {
        Interview interviewExpected = new Interview(UUID.randomUUID().toString(), "aa@gmail.com", "ra@levio.ca", "Java Developer", StateOfInterview.OPEN, LevelOfExpertise.SENIOR, TypeOfInterview.NOT_URGENT);
        when(interviewRepository.saveAndFlush(any(Interview.class))).thenReturn(interviewExpected);
        Interview interviewActual = interviewService.saveInterview(interviewExpected);
        assertEquals(interviewExpected, interviewActual);
    }

    @Test
    public void getInterviewsTest() {
        List<Interview> interviewsExpected = new ArrayList();
        interviewsExpected.add(new Interview(UUID.randomUUID().toString(), "aa@gmail.com", "ra@levio.ca", "Java Developer", StateOfInterview.OPEN, LevelOfExpertise.SENIOR, TypeOfInterview.NOT_URGENT));
        interviewsExpected.add(new Interview(UUID.randomUUID().toString(), "ab@gmail.com", "rb@levio.ca", ".NET Developer", StateOfInterview.OPEN, LevelOfExpertise.INTERMEDIATE, TypeOfInterview.URGENT));
        interviewsExpected.add(new Interview(UUID.randomUUID().toString(), "ac@gmail.com", "rc@levio.ca", "Java Developer", StateOfInterview.OPEN, LevelOfExpertise.JUNIOR, TypeOfInterview.NOT_URGENT));
        interviewsExpected.add(new Interview(UUID.randomUUID().toString(), "ad@gmail.com", "rd@levio.ca", ".NET Developer", StateOfInterview.OPEN, LevelOfExpertise.INTERMEDIATE, TypeOfInterview.URGENT));
        when(interviewRepository.findAll()).thenReturn(interviewsExpected);
        List<Interview> interviewsActual = interviewService.getInterviews();
        assertEquals(interviewsExpected, interviewsActual);
    }
}
