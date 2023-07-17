package ca.levio.interviewrequest.messageevent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class NewInterviewRequestMessageEvent extends MessageEvent {
    public static final String TOPIC = "NewInterviewRequestMessageEvent";
    private String technicalAdvisor;
    private String interview;
    private String interviewRequest;
}
