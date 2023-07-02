package ca.levio.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class LinksConfigProperties {
    
    @Value("${interview-detail-link}")
    private String interviewDetailLink;
    @Value("${accept-interview-request-link}")
    private String acceptInterviewRequestLink;
    @Value("${reject-interview-request-link}")
    private String rejectInterviewRequestLink;
}
