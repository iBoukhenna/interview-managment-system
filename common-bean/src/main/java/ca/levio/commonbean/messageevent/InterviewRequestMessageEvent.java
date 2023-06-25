package ca.levio.commonbean.messageevent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class InterviewRequestMessageEvent extends MessageEvent {
    public static final String TOPIC = "InterviewRequest";
    private String interview;
    private Integer x;
    private String jobPosition;
    private String levelOfExpertise;
}
