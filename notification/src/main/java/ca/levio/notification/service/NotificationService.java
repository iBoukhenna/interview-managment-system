package ca.levio.notification.service;

import org.springframework.stereotype.Service;

import ca.levio.commonbean.messageevent.NewInterviewRequestMessageEvent;
import ca.levio.mailmaker.maildtos.MailGeneratorResponseDto;
import ca.levio.mailmaker.maildtos.NewInterviewRequestMailDataRequestDto;
import ca.levio.mailmaker.service.MailGeneratorService;
import ca.levio.notification.config.LinksConfigProperties;
import ca.levio.notification.dto.NotificationDto;
import ca.levio.notification.mapper.NewInterviewRequestMessageEventMailDataDtoMapper;
import ca.levio.notification.mapper.NotificationDtoMailGeneratorResponseDtoMapper;
import ca.levio.notification.sender.NotificationSender;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificationService {

    private final MailGeneratorService mailGeneratorService;
    private final NotificationDtoMailGeneratorResponseDtoMapper notificationDtoMailGeneratorResponseDtoMapper;
    private final NotificationSender notificationSender;
    private final NewInterviewRequestMessageEventMailDataDtoMapper newInterviewRequestMessageEventMailDataDtoMapper;
    private final LinksConfigProperties linksConfigProperties;

    public void sendNewInterviewRequestNotification(NewInterviewRequestMessageEvent newInterviewRequestMessageEvent) {
        NewInterviewRequestMailDataRequestDto newInterviewRequestMailDataDto =  newInterviewRequestMessageEventMailDataDtoMapper.newInterviewRequestMessageEventToNewInterviewRequestMailDataRequestDto(newInterviewRequestMessageEvent, linksConfigProperties);
        MailGeneratorResponseDto mailGeneratorResponseDto = mailGeneratorService.generateNewInterviewRequestMailResponseDto(newInterviewRequestMailDataDto);
        NotificationDto notificationDto = notificationDtoMailGeneratorResponseDtoMapper.mailGeneratorResponseDtoToNotificationDto(mailGeneratorResponseDto);
        notificationSender.send(notificationDto);
    }
}
