package ca.levio.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GenerateInterviewRequestDto {
    private String interview;
    private Integer x;
    private String jobPosition;
    private String levelOfExpertise;
    
}
