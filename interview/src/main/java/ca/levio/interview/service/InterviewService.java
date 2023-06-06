package ca.levio.interview.service;

import ca.levio.interview.dto.InterviewCreationRequest;
import ca.levio.interview.dto.InterviewRequestCreationRequest;
import ca.levio.interview.enums.LevelOfExpertise;
import ca.levio.interview.enums.StateOfInterview;
import ca.levio.interview.enums.TypeOfInterview;
import ca.levio.interview.kafka.KafkaMessageProducer;
import ca.levio.interview.model.Interview;
import ca.levio.interview.repository.InterviewRepository;

import org.springframework.stereotype.Service;

@Service
public record InterviewService(InterviewRepository interviewRepository, KafkaMessageProducer kafkaMessageProducer) {

    public void createInterview(InterviewCreationRequest request) {

        LevelOfExpertise levelOfExpertise = LevelOfExpertise.valueOf(request.levelOfExpertise());
        TypeOfInterview typeOfInterview = TypeOfInterview.valueOf(request.typeOfInterview());
        Interview interview = Interview.builder().applicat(request.applicant())
                .recruiter(request.recruiter())
                .jobPosition(request.jobPosition())
                .stateOfInterview(StateOfInterview.OPEN)
                .levelOfExpertise(levelOfExpertise)
                .typeOfInterview(typeOfInterview).build();

        interview = interviewRepository.saveAndFlush(interview);
        
        InterviewRequestCreationRequest interviewRequestCreationRequest = new InterviewRequestCreationRequest(
            interview.getId(),
            typeOfInterview.getX(),
            request.jobPosition(),
            request.levelOfExpertise()
        );

        kafkaMessageProducer.publish("interviewrequest.topic", "internal.interviewrequest.key", interviewRequestCreationRequest);
    }

}
