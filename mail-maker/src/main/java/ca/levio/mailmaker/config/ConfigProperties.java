package ca.levio.mailmaker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class ConfigProperties {

    @Value("${new-interview-request.subject}")
    private String newInterviewRequestSubject;

    @Value("${new-interview-request.template}")
    private String newInterviewRequestTemplate;

    @Value("${interview-accepted.template}")
    private String interviewAcceptedTemplate;

    @Value("${interview-accepted.subject}")
    private String interviewAcceptedSubject;

    @Value("${interview-already-accepted.template}")
    private String interviewAlreadyAcceptedTemplate;

    @Value("${interview-already-accepted.subject}")
    private String interviewAlreadyAcceptedSubject;

    @Value("${interview-assigned.template}")
    private String interviewAssignedTemplate;

    @Value("${interview-assigned.subject}")
    private String interviewAssignedSubject;

    @Value("${interview-declined.template}")
    private String interviewDeclinedTemplate;

    @Value("${interview-declined.subject}")
    private String interviewDeclinedSubject;

}
