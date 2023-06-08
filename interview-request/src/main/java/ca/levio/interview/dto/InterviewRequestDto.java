package ca.levio.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InterviewRequestDto{
    private Integer interview;
    private Integer x;
    private String jobPosition;
    private String levelOfExpertise;
    
}
