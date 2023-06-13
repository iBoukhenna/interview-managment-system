package ca.levio.interviewrequest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExternalService {
    @Value("${technical-advisor.url}")
    private String technicalAdvisorUrl;

    public String returnTechnicalServiceUrl() {
        return technicalAdvisorUrl;
    }
}
