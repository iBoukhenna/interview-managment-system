package ca.levio.notification.service;

import org.springframework.stereotype.Service;

import ca.levio.commonbean.messageevent.InterviewAcceptedMessageEvent;
import ca.levio.commonbean.messageevent.InterviewAlreadyAcceptedMessageEvent;
import ca.levio.commonbean.messageevent.InterviewAssignedMessageEvent;
import ca.levio.commonbean.messageevent.InterviewDeclinedMessageEvent;
import ca.levio.commonbean.messageevent.NewInterviewRequestMessageEvent;
import ca.levio.mailmaker.maildtos.InterviewAcceptedMailDataRequestDto;
import ca.levio.mailmaker.maildtos.InterviewAlreadyAcceptedMailDataRequestDto;
import ca.levio.mailmaker.maildtos.InterviewAssignedMailDataRequestDto;
import ca.levio.mailmaker.maildtos.InterviewDeclinedMailDataRequestDto;
import ca.levio.mailmaker.maildtos.MailGeneratorResponseDto;
import ca.levio.mailmaker.maildtos.NewInterviewRequestMailDataRequestDto;
import ca.levio.mailmaker.service.MailGeneratorService;
import ca.levio.notification.config.LinksConfigProperties;
import ca.levio.notification.dto.NotificationDto;
import ca.levio.notification.mapper.NewInterviewRequestMessageEventMailDataDtoMapper;
import ca.levio.notification.mapper.InterviewAcceptedMessageEventMailDataDtoMapper;
import ca.levio.notification.mapper.InterviewAlreadyAcceptedMessageEventMailDataDtoMapper;
import ca.levio.notification.mapper.InterviewAssignedMessageEventMailDataDtoMapper;
import ca.levio.notification.mapper.InterviewDeclinedMessageEventMailDataDtoMapper;
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
    private final InterviewAcceptedMessageEventMailDataDtoMapper interviewAcceptedMessageEventMailDataDtoMapper;
    private final InterviewAlreadyAcceptedMessageEventMailDataDtoMapper interviewAlreadyAcceptedMessageEventMailDataDtoMapper;
    private final InterviewAssignedMessageEventMailDataDtoMapper interviewAssignedMessageEventMailDataDtoMapper;
    private final InterviewDeclinedMessageEventMailDataDtoMapper interviewDeclinedMessageEventMailDataDtoMapper;
    private final LinksConfigProperties linksConfigProperties;

    public void sendNewInterviewRequestNotification(NewInterviewRequestMessageEvent newInterviewRequestMessageEvent) {
        NewInterviewRequestMailDataRequestDto newInterviewRequestMailDataDto =  newInterviewRequestMessageEventMailDataDtoMapper.newInterviewRequestMessageEventToNewInterviewRequestMailDataRequestDto(newInterviewRequestMessageEvent, linksConfigProperties);
        MailGeneratorResponseDto mailGeneratorResponseDto = mailGeneratorService.generateNewInterviewRequestMailResponseDto(newInterviewRequestMailDataDto);
        NotificationDto notificationDto = notificationDtoMailGeneratorResponseDtoMapper.mailGeneratorResponseDtoToNotificationDto(mailGeneratorResponseDto);
        notificationSender.send(notificationDto);
    }

    public void sendInterviewAcceptedNotification(InterviewAcceptedMessageEvent interviewAcceptedMessageEvent) {
        InterviewAcceptedMailDataRequestDto interviewAcceptedMailDataRequestDto =  interviewAcceptedMessageEventMailDataDtoMapper.interviewAcceptedMessageEventToInterviewAcceptedMailDataRequestDto(interviewAcceptedMessageEvent, linksConfigProperties);
        MailGeneratorResponseDto mailGeneratorResponseDto = mailGeneratorService.generateInterviewAcceptedMailResponseDto(interviewAcceptedMailDataRequestDto);
        NotificationDto notificationDto = notificationDtoMailGeneratorResponseDtoMapper.mailGeneratorResponseDtoToNotificationDto(mailGeneratorResponseDto);
        notificationSender.send(notificationDto);
    }

    public void sendInterviewAlreadyAcceptedNotification(InterviewAlreadyAcceptedMessageEvent interviewAlreadyAcceptedMessageEvent) {
        InterviewAlreadyAcceptedMailDataRequestDto interviewAlreadyAcceptedMailDataRequestDto =  interviewAlreadyAcceptedMessageEventMailDataDtoMapper.interviewAlreadyAcceptedMessageEventToInterviewAlreadyAcceptedMailDataRequestDto(interviewAlreadyAcceptedMessageEvent, linksConfigProperties);
        MailGeneratorResponseDto mailGeneratorResponseDto = mailGeneratorService.generateInterviewAlreadyAcceptedMailResponseDto(interviewAlreadyAcceptedMailDataRequestDto);
        NotificationDto notificationDto = notificationDtoMailGeneratorResponseDtoMapper.mailGeneratorResponseDtoToNotificationDto(mailGeneratorResponseDto);
        notificationSender.send(notificationDto);
    }

    public void sendInterviewAssignedNotification(InterviewAssignedMessageEvent interviewAssignedMessageEvent) {
        InterviewAssignedMailDataRequestDto interviewAssignedMailDataRequestDto =  interviewAssignedMessageEventMailDataDtoMapper.interviewAssignedMessageEventToInterviewAssignedMailDataRequestDto(interviewAssignedMessageEvent, linksConfigProperties);
        MailGeneratorResponseDto mailGeneratorResponseDto = mailGeneratorService.generateInterviewAssignedMailResponseDto(interviewAssignedMailDataRequestDto);
        NotificationDto notificationDto = notificationDtoMailGeneratorResponseDtoMapper.mailGeneratorResponseDtoToNotificationDto(mailGeneratorResponseDto);
        notificationSender.send(notificationDto);
    }

    public void sendInterviewDeclinedNotification(InterviewDeclinedMessageEvent interviewDeclinedMessageEvent) {
        InterviewDeclinedMailDataRequestDto interviewDeclinedMailDataRequestDto =  interviewDeclinedMessageEventMailDataDtoMapper.interviewDeclinedMessageEventToInterviewDeclinedMailDataRequestDto(interviewDeclinedMessageEvent, linksConfigProperties);
        MailGeneratorResponseDto mailGeneratorResponseDto = mailGeneratorService.generateInterviewDeclinedMailResponseDto(interviewDeclinedMailDataRequestDto);
        NotificationDto notificationDto = notificationDtoMailGeneratorResponseDtoMapper.mailGeneratorResponseDtoToNotificationDto(mailGeneratorResponseDto);
        notificationSender.send(notificationDto);
    }


}
