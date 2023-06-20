package ca.levio.commonbean.messageevent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InterviewRequestMessageEvent{
    private String interview;
    private Integer x;
    private String jobPosition;
    private String levelOfExpertise;
    
}
