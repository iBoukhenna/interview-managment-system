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

import ca.levio.commonbean.dto.InterviewDto;
import ca.levio.commonbean.messageevent.NewInterviewMessageEvent;
import ca.levio.interview.enums.LevelOfExpertise;
import ca.levio.interview.enums.StateOfInterview;
import ca.levio.interview.enums.TypeOfInterview;
import ca.levio.interview.mapper.InterviewDtoMapper;
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
        InterviewDto interviewDto = new InterviewDto("1", "Ahmed@gmail.com"
            , "A01", "Amine@levio.ca", "Amine", "Java Developer"
            , LevelOfExpertise.INTERMEDIATE.getCode(), TypeOfInterview.NOT_URGENT.getCode(), StateOfInterview.OPEN.toString(), 2, 5);

        Interview interviewExpected = interviewDtoMapper.interviewDtoToInterview(interviewDto);

        Mockito.doNothing().when(messageQueueProducer).send(any(NewInterviewMessageEvent.class));
        when(interviewRepository.saveAndFlush(any(Interview.class))).thenReturn(interviewExpected);

        Interview interviewActual = interviewService.createInterview(interviewDto);
        assertEquals(interviewExpected, interviewActual);
    }

    @Test
    void saveInterviewTest() {
        Interview interviewExpected = new Interview(UUID.randomUUID().toString(), "aa@gmail.com", "RA01", "ra@levio.ca", "RA", "Java Developer", StateOfInterview.OPEN, LevelOfExpertise.SENIOR, TypeOfInterview.NOT_URGENT);
        when(interviewRepository.saveAndFlush(any(Interview.class))).thenReturn(interviewExpected);
        Interview interviewActual = interviewService.saveInterview(interviewExpected);
        assertEquals(interviewExpected, interviewActual);
    }

    @Test
    public void getInterviewsTest() {
        List<Interview> interviewsExpected = new ArrayList();
        interviewsExpected.add(new Interview(UUID.randomUUID().toString(), "aa@gmail.com", "RA01", "ra@levio.ca", "RA", "Java Developer", StateOfInterview.OPEN, LevelOfExpertise.SENIOR, TypeOfInterview.NOT_URGENT));
        interviewsExpected.add(new Interview(UUID.randomUUID().toString(), "ab@gmail.com", "RB01", "rb@levio.ca", "RB", ".NET Developer", StateOfInterview.OPEN, LevelOfExpertise.INTERMEDIATE, TypeOfInterview.URGENT));
        interviewsExpected.add(new Interview(UUID.randomUUID().toString(), "ac@gmail.com", "RC01", "rc@levio.ca", "RC", "Java Developer", StateOfInterview.OPEN, LevelOfExpertise.JUNIOR, TypeOfInterview.NOT_URGENT));
        interviewsExpected.add(new Interview(UUID.randomUUID().toString(), "ad@gmail.com", "RD01", "rd@levio.ca", "RD", ".NET Developer", StateOfInterview.OPEN, LevelOfExpertise.INTERMEDIATE, TypeOfInterview.URGENT));
        when(interviewRepository.findAll()).thenReturn(interviewsExpected);
        List<InterviewDto> interviewsActual = interviewService.getInterviews();
        assertEquals(interviewsExpected.size(), interviewsActual.size());
    }
}
