package ca.levio.commonbean.messageevent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class InterviewDeclinedMessageEvent extends MessageEvent {
    public static final String TOPIC = "InterviewDeclinedMessageEvent";
    private String technicalAdvisorEmail;
    private String technicalAdvisorName;
    private String interview;
}
