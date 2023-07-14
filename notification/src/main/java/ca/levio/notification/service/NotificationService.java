package ca.levio.notification.service;

import org.springframework.stereotype.Service;

import ca.levio.messagequeue.messageevent.InterviewAcceptedMessageEvent;
import ca.levio.messagequeue.messageevent.InterviewAlreadyAcceptedMessageEvent;
import ca.levio.messagequeue.messageevent.InterviewAssignedMessageEvent;
import ca.levio.messagequeue.messageevent.InterviewDeclinedMessageEvent;
import ca.levio.messagequeue.messageevent.NewInterviewRequestMessageEvent;
import ca.levio.messagequeue.messageevent.NoAvailibleTechnicalAdvisorMessageEvent;
import ca.levio.notification.config.LinksConfigProperties;
import ca.levio.notification.dto.NotificationDto;
import ca.levio.notification.maildtos.InterviewAcceptedMailDataRequestDto;
import ca.levio.notification.maildtos.InterviewAlreadyAcceptedMailDataRequestDto;
import ca.levio.notification.maildtos.InterviewAssignedMailDataRequestDto;
import ca.levio.notification.maildtos.InterviewDeclinedMailDataRequestDto;
import ca.levio.notification.maildtos.MailGeneratorResponseDto;
import ca.levio.notification.maildtos.NewInterviewRequestMailDataRequestDto;
import ca.levio.notification.maildtos.NoAvailibleTechnicalAdvisorMailDataRequestDto;
import ca.levio.notification.mapper.NewInterviewRequestMessageEventMailDataDtoMapper;
import ca.levio.notification.mapper.NoAvailibleTechnicalAdvisorMessageEventMailDataDtoMapper;
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
    private final NoAvailibleTechnicalAdvisorMessageEventMailDataDtoMapper noAvailibleTechnicalAdvisorMessageEventMailDataDtoMapper;
    private final LinksConfigProperties linksConfigProperties;
    private final TechnicalAdvisorService technicalAdvisorService;
    private final RecruiterService recruiterService;

    public void sendNewInterviewRequestNotification(NewInterviewRequestMessageEvent newInterviewRequestMessageEvent) {
        NewInterviewRequestMailDataRequestDto newInterviewRequestMailDataDto =  newInterviewRequestMessageEventMailDataDtoMapper.newInterviewRequestMessageEventToNewInterviewRequestMailDataRequestDto(newInterviewRequestMessageEvent, linksConfigProperties, technicalAdvisorService);
        MailGeneratorResponseDto mailGeneratorResponseDto = mailGeneratorService.generateNewInterviewRequestMailResponseDto(newInterviewRequestMailDataDto);
        NotificationDto notificationDto = notificationDtoMailGeneratorResponseDtoMapper.mailGeneratorResponseDtoToNotificationDto(mailGeneratorResponseDto);
        notificationSender.send(notificationDto);
    }

    public void sendInterviewAcceptedNotification(InterviewAcceptedMessageEvent interviewAcceptedMessageEvent) {
        InterviewAcceptedMailDataRequestDto interviewAcceptedMailDataRequestDto =  interviewAcceptedMessageEventMailDataDtoMapper.interviewAcceptedMessageEventToInterviewAcceptedMailDataRequestDto(interviewAcceptedMessageEvent, linksConfigProperties, technicalAdvisorService);
        MailGeneratorResponseDto mailGeneratorResponseDto = mailGeneratorService.generateInterviewAcceptedMailResponseDto(interviewAcceptedMailDataRequestDto);
        NotificationDto notificationDto = notificationDtoMailGeneratorResponseDtoMapper.mailGeneratorResponseDtoToNotificationDto(mailGeneratorResponseDto);
        notificationSender.send(notificationDto);
    }

    public void sendInterviewAlreadyAcceptedNotification(InterviewAlreadyAcceptedMessageEvent interviewAlreadyAcceptedMessageEvent) {
        InterviewAlreadyAcceptedMailDataRequestDto interviewAlreadyAcceptedMailDataRequestDto =  interviewAlreadyAcceptedMessageEventMailDataDtoMapper.interviewAlreadyAcceptedMessageEventToInterviewAlreadyAcceptedMailDataRequestDto(interviewAlreadyAcceptedMessageEvent, linksConfigProperties, technicalAdvisorService);
        MailGeneratorResponseDto mailGeneratorResponseDto = mailGeneratorService.generateInterviewAlreadyAcceptedMailResponseDto(interviewAlreadyAcceptedMailDataRequestDto);
        NotificationDto notificationDto = notificationDtoMailGeneratorResponseDtoMapper.mailGeneratorResponseDtoToNotificationDto(mailGeneratorResponseDto);
        notificationSender.send(notificationDto);
    }

    public void sendInterviewAssignedNotification(InterviewAssignedMessageEvent interviewAssignedMessageEvent) {
        InterviewAssignedMailDataRequestDto interviewAssignedMailDataRequestDto =  interviewAssignedMessageEventMailDataDtoMapper.interviewAssignedMessageEventToInterviewAssignedMailDataRequestDto(interviewAssignedMessageEvent, linksConfigProperties, recruiterService);
        MailGeneratorResponseDto mailGeneratorResponseDto = mailGeneratorService.generateInterviewAssignedMailResponseDto(interviewAssignedMailDataRequestDto);
        NotificationDto notificationDto = notificationDtoMailGeneratorResponseDtoMapper.mailGeneratorResponseDtoToNotificationDto(mailGeneratorResponseDto);
        notificationSender.send(notificationDto);
    }

    public void sendInterviewDeclinedNotification(InterviewDeclinedMessageEvent interviewDeclinedMessageEvent) {
        InterviewDeclinedMailDataRequestDto interviewDeclinedMailDataRequestDto =  interviewDeclinedMessageEventMailDataDtoMapper.interviewDeclinedMessageEventToInterviewDeclinedMailDataRequestDto(interviewDeclinedMessageEvent, linksConfigProperties, technicalAdvisorService);
        MailGeneratorResponseDto mailGeneratorResponseDto = mailGeneratorService.generateInterviewDeclinedMailResponseDto(interviewDeclinedMailDataRequestDto);
        NotificationDto notificationDto = notificationDtoMailGeneratorResponseDtoMapper.mailGeneratorResponseDtoToNotificationDto(mailGeneratorResponseDto);
        notificationSender.send(notificationDto);
    }

    public void sendNoAvailibleTechnicalAdvisorNotification(NoAvailibleTechnicalAdvisorMessageEvent noAvailibleTechnicalAdvisorMessageEvent) {
        NoAvailibleTechnicalAdvisorMailDataRequestDto noAvailibleTechnicalAdvisorMailDataRequestDto =  noAvailibleTechnicalAdvisorMessageEventMailDataDtoMapper.noAvailibleTechnicalAdvisorMessageEventToNoAvailibleTechnicalAdvisorMailDataRequestDto(noAvailibleTechnicalAdvisorMessageEvent, linksConfigProperties, recruiterService);
        MailGeneratorResponseDto mailGeneratorResponseDto = mailGeneratorService.generateNoAvailibleTechnicalAdvisorMailResponseDto(noAvailibleTechnicalAdvisorMailDataRequestDto);
        NotificationDto notificationDto = notificationDtoMailGeneratorResponseDtoMapper.mailGeneratorResponseDtoToNotificationDto(mailGeneratorResponseDto);
        notificationSender.send(notificationDto);
    }
}
