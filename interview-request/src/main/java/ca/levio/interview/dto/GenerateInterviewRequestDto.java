package ca.levio.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GenerateInterviewRequestDto{
    private String interview;
    private Integer x;
    private String jobPosition;
    private String levelOfExpertise;
    
}
