package ca.levio.notification.sender;

import ca.levio.notification.dto.NotificationDto;

public interface NotificationSender {
    
    void send(NotificationDto NotificationDto);
}
