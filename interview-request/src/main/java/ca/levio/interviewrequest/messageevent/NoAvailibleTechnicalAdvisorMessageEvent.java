package ca.levio.interviewrequest.messageevent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class NoAvailibleTechnicalAdvisorMessageEvent extends MessageEvent {
    public static final String TOPIC = "NoAvailibleTechnicalAdvisorMessageEvent";
    private String recruiter;
    private String interview;
}
