package ca.levio.notification.mapper;

import org.mapstruct.Mapper;

import ca.levio.mailmaker.maildtos.MailGeneratorResponseDto;
import ca.levio.notification.dto.NotificationDto;

@Mapper(componentModel = "spring")
public abstract class NotificationDtoMailGeneratorResponseDtoMapper {
    
    public abstract MailGeneratorResponseDto notificationDtoToMailGeneratorResponseDto(NotificationDto notificationDto);

    public abstract NotificationDto mailGeneratorResponseDtoToNotificationDto(MailGeneratorResponseDto mailGeneratorResponseDto);

}
