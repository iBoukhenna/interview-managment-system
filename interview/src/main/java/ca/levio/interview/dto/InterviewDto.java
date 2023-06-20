package ca.levio.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewDto {
    private String applicant;
    private String recruiter;
    private String jobPosition;
    private String levelOfExpertise;
    private String typeOfInterview;
}
