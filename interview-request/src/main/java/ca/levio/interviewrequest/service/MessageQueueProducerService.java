package ca.levio.interviewrequest.service;

import ca.levio.interviewrequest.dto.InterviewDto;
import ca.levio.interviewrequest.mapper.InterviewDtoNoAvailibleTechnicalAdvisorMessageEventMapper;
import ca.levio.interviewrequest.mapper.InterviewRequestAllInterviewRequestRejectedMessageEventMapper;
import ca.levio.interviewrequest.mapper.InterviewRequestInterviewAcceptedMessageEventMapper;
import ca.levio.interviewrequest.mapper.InterviewRequestInterviewAlreadyAcceptedMessageEventMapper;
import ca.levio.interviewrequest.mapper.InterviewRequestInterviewAssignedMessageEventMapper;
import ca.levio.interviewrequest.mapper.InterviewRequestInterviewDeclinedMessageEventMapper;
import ca.levio.interviewrequest.mapper.InterviewRequestNewInterviewRequestMessageEventMapper;
import ca.levio.interviewrequest.model.InterviewRequest;
import lombok.AllArgsConstructor;

import ca.levio.interviewrequest.messageevent.AllInterviewRequestRejectedMessageEvent;
import ca.levio.interviewrequest.messageevent.InterviewAcceptedMessageEvent;
import ca.levio.interviewrequest.messageevent.InterviewAlreadyAcceptedMessageEvent;
import ca.levio.interviewrequest.messageevent.InterviewAssignedMessageEvent;
import ca.levio.interviewrequest.messageevent.InterviewDeclinedMessageEvent;
import ca.levio.interviewrequest.messageevent.NewInterviewRequestMessageEvent;
import ca.levio.interviewrequest.messageevent.NoAvailibleTechnicalAdvisorMessageEvent;
import ca.levio.interviewrequest.producer.MessageQueueProducer;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MessageQueueProducerService {

    private final InterviewRequestAllInterviewRequestRejectedMessageEventMapper interviewRequestAllInterviewRequestRejectedMessageEventMapper;
    private final InterviewRequestInterviewAcceptedMessageEventMapper interviewRequestInterviewAcceptedMessageEventMapper;
    private final InterviewRequestInterviewAlreadyAcceptedMessageEventMapper interviewRequestInterviewAlreadyAcceptedMessageEventMapper;
    private final InterviewRequestInterviewAssignedMessageEventMapper interviewRequestInterviewAssignedMessageEventMapper;
    private final InterviewRequestInterviewDeclinedMessageEventMapper interviewRequestInterviewDeclinedMessageEventMapper;
    private final InterviewRequestNewInterviewRequestMessageEventMapper interviewRequestNewInterviewRequestMessageEventMapper;
    private final InterviewDtoNoAvailibleTechnicalAdvisorMessageEventMapper interviewDtoNoAvailibleTechnicalAdvisorMessageEventMapper;

    private final MessageQueueProducer messageQueueProducer;

    public void sendNewInterviewRequestMessageEvent(InterviewRequest interviewRequest) {
        NewInterviewRequestMessageEvent newInterviewRequestMessageEvent = interviewRequestNewInterviewRequestMessageEventMapper.interviewRequestToNewInterviewRequestMessageEvent(interviewRequest);
        messageQueueProducer.send(newInterviewRequestMessageEvent);
    }

    public void sendInterviewAcceptedMessageEvent(InterviewRequest interviewRequest) {
        InterviewAcceptedMessageEvent interviewAcceptedMessageEvent = interviewRequestInterviewAcceptedMessageEventMapper.interviewRequestToInterviewAcceptedMessageEvent(interviewRequest);
        messageQueueProducer.send(interviewAcceptedMessageEvent);
    }

    public void sendInterviewAlreadyAcceptedMessageEvent(InterviewRequest interviewRequest) {
        InterviewAlreadyAcceptedMessageEvent interviewAlreadyAcceptedMessageEvent = interviewRequestInterviewAlreadyAcceptedMessageEventMapper.interviewRequestToInterviewAlreadyAcceptedMessageEvent(interviewRequest);
        messageQueueProducer.send(interviewAlreadyAcceptedMessageEvent);
    }

    public void sendInterviewAssignedMessageEvent(InterviewRequest interviewRequest) {
        InterviewAssignedMessageEvent interviewAssignedMessageEvent = interviewRequestInterviewAssignedMessageEventMapper.interviewRequestToInterviewAssignedMessageEvent(interviewRequest);
        messageQueueProducer.send(interviewAssignedMessageEvent);
    }

    public void sendNoAvailibleTechnicalAdvisorMessageEvent(InterviewDto interviewDto) {
        NoAvailibleTechnicalAdvisorMessageEvent noAvailibleTechnicalAdvisorMessageEvent = interviewDtoNoAvailibleTechnicalAdvisorMessageEventMapper.interviewDtoToNoAvailibleTechnicalAdvisorMessageEvent(interviewDto);
        messageQueueProducer.send(noAvailibleTechnicalAdvisorMessageEvent);
    }

    public void sendInterviewDeclinedMessageEvent(InterviewRequest interviewRequest) {
        InterviewDeclinedMessageEvent interviewDeclinedMessageEvent = interviewRequestInterviewDeclinedMessageEventMapper.interviewRequestToInterviewDeclinedMessageEvent(interviewRequest);
        messageQueueProducer.send(interviewDeclinedMessageEvent);
    }

    public void sendAllInterviewRequestRejectedMessageEvent(InterviewRequest interviewRequest) {
        AllInterviewRequestRejectedMessageEvent allInterviewRequestRejectedMessageEvent = interviewRequestAllInterviewRequestRejectedMessageEventMapper.interviewRequestToAllInterviewRequestRejectedMessageEvent(interviewRequest);
        messageQueueProducer.send(allInterviewRequestRejectedMessageEvent);
    }

}