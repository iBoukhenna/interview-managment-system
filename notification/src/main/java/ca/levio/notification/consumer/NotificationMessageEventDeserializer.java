package ca.levio.notification.consumer;


import org.springframework.kafka.support.serializer.JsonDeserializer;

import ca.levio.commonbean.messageevent.NotificationMessageEvent;


public class NotificationMessageEventDeserializer extends JsonDeserializer<NotificationMessageEvent> {

	public NotificationMessageEventDeserializer() {
		super(NotificationMessageEvent.class);
	}

}
