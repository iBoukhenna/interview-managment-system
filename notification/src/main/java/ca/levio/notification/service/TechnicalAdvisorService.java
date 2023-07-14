package ca.levio.notification.service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import ca.levio.notification.dto.TechnicalAdvisorDto;
import ca.levio.notification.openfeign.TechnicalAdvisorClient;

@AllArgsConstructor
@Service
public class TechnicalAdvisorService {

    private final TechnicalAdvisorClient technicalAdvisorClient;

    public TechnicalAdvisorDto getTechnicalAdvisor(String id) {
        System.out.println("TechnicalAdvisorService.getTechnicalAdvisor.id=" + id);
        return technicalAdvisorClient.getTechnicalAdvisor(id).getBody();
    }
}