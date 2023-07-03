package ca.levio.interview.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.levio.interview.dto.InterviewDto;
import ca.levio.interview.enums.LevelOfExpertise;
import ca.levio.interview.enums.StateOfInterview;
import ca.levio.interview.enums.TypeOfInterview;
import ca.levio.interview.mapper.InterviewDtoMapper;
import ca.levio.interview.model.Interview;
import ca.levio.interview.service.InterviewService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class InterviewControllerTests {

    private MockMvc mockMvc;

    @Mock
    private InterviewService interviewService;

    @Mock
    private InterviewDtoMapper interviewDtoMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private InterviewController interviewController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(interviewController).build();
        objectMapper = new ObjectMapper();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldCreateInterview() throws Exception {
        InterviewDto interviewDto = new InterviewDto("Ahmed@gmail.com"
            , UUID.randomUUID().toString(), "Amine@levio.ca", "Amine", "Java Developer"
            , LevelOfExpertise.INTERMEDIATE.getCode(), TypeOfInterview.NOT_URGENT.getCode());

        Interview interivew = interviewDtoMapper.interviewDtoToInterview(interviewDto);

        when(interviewService.createInterview(any(InterviewDto.class))).thenReturn(interivew);

        // When
        mockMvc.perform(post("/api/v1/interviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(interviewDto)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldReturnListOfTutorials() throws Exception {
        List<Interview> interviews = new ArrayList<>(
        Arrays.asList(new Interview(UUID.randomUUID().toString(), "a@gmail.com", "a01", "a@levio.ca", "ra", "Java developer", StateOfInterview.OPEN, LevelOfExpertise.INTERMEDIATE, TypeOfInterview.NOT_URGENT),
            new Interview(UUID.randomUUID().toString(), "b@gmail.com", "b01", "b@levio.ca", "rb", "Java developer", StateOfInterview.OPEN, LevelOfExpertise.INTERMEDIATE, TypeOfInterview.URGENT),
            new Interview(UUID.randomUUID().toString(), "c@gmail.com", "c01", "c@levio.ca", "rc", "Java developer", StateOfInterview.OPEN, LevelOfExpertise.INTERMEDIATE, TypeOfInterview.NOT_URGENT)));

        when(interviewService.getInterviews()).thenReturn(interviews);
        mockMvc.perform(get("/api/v1/interviews"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(interviews.size()))
            .andDo(print());
    }
}
