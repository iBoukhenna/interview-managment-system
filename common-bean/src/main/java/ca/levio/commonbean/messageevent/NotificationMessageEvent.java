package ca.levio.commonbean.messageevent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class NotificationMessageEvent extends MessageEvent {
    public static final String TOPIC = "Notification";
    private String subject;
    private String content;
    private String to;
}
