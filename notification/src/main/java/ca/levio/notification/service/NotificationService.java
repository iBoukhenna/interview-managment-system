package ca.levio.notification.service;

import ca.levio.commonbean.messageevent.NotificationMessageEvent;

public interface NotificationService {
    
    void send(NotificationMessageEvent notificationMessageEvent);
}
