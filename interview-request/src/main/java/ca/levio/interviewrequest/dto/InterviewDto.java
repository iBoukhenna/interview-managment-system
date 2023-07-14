package ca.levio.interviewrequest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewDto {
    private String interview;
    private String recruiter;
    private Integer numberOfTechnicalAdvisorByBatch;
    private Integer delayBeforeRetrying;
    private String jobPosition;
    private String levelOfExpertise;
    private String stateOfInterview;
}
