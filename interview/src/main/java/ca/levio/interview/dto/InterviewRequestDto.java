package ca.levio.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InterviewRequestDto{
    private Integer interview;
    private Integer x;
    private String jobPosition;
    private String levelOfExpertise;
    
}
