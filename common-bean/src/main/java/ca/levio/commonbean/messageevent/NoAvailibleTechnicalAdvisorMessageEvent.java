package ca.levio.commonbean.messageevent;

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
    private String recruiterEmail;
    private String recruiterName;
    private String interview;
}
