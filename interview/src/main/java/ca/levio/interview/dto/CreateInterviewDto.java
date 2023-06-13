package ca.levio.interview.dto;

import lombok.Data;

@Data
public class CreateInterviewDto {
    private String applicant;
    private String recruiter;
    private String jobPosition;
    private String levelOfExpertise;
    private String typeOfInterview;
}
