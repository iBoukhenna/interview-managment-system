package ca.levio.interviewrequest.messageevent;

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
    private String recruiter;
    private String interview;
    private String technicalAdvisor;
}
