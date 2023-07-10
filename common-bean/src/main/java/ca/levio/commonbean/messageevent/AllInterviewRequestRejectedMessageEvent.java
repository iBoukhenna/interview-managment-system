package ca.levio.commonbean.messageevent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AllInterviewRequestRejectedMessageEvent extends MessageEvent {
    public static final String TOPIC = "AllInterviewRequestRejectedMessageEvent";
    private String interview;
}
