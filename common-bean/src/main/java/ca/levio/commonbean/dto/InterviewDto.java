package ca.levio.commonbean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewDto {
    private String interview;
    private String applicant;
    private String recruiter;
    private String recruiterEmail;
    private String recruiterName;
    private String jobPosition;
    private String levelOfExpertise;
    private String typeOfInterview;
    private String stateOfInterview;
    private Integer numberOfTechnicalAdvisorByBatch;
    private Integer delayBeforeRetrying;
}
