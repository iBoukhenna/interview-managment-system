package ca.levio.commonbean.messageevent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class InterviewAssignedMessageEvent extends MessageEvent {
    public static final String TOPIC = "InterviewAssignedMessageEvent";
    private String recruiterEmail;
    private String recruiterName;
    private String interview;
    private String technicalAdvisor;
}
