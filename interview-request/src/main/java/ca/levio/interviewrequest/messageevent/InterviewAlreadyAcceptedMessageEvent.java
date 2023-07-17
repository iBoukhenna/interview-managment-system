package ca.levio.interviewrequest.messageevent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class InterviewAlreadyAcceptedMessageEvent extends MessageEvent {
    public static final String TOPIC = "InterviewAlreadyAcceptedMessageEvent";
    private String technicalAdvisor;
    private String interview;
}
