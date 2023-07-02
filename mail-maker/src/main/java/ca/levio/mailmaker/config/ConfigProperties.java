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
}
