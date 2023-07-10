package ca.levio.commonbean.messageevent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class NewInterviewMessageEvent extends MessageEvent {
    public static final String TOPIC = "NewInterviewMessageEvent";
    private String interview;
    private String recruiter;
    private String recruiterName;
    private String recruiterEmail;
    private Integer numberOfTechnicalAdvisorByBatch;
    private Integer delayBeforeRetrying;
    private String jobPosition;
    private String levelOfExpertise;
    private String stateOfInterview;
}
