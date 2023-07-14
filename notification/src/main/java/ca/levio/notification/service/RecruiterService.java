package ca.levio.notification.service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import ca.levio.notification.dto.RecruiterDto;
import ca.levio.notification.openfeign.RecruiterClient;

@AllArgsConstructor
@Service
public class RecruiterService {

    private final RecruiterClient recruiterClient;

    public RecruiterDto getRecruiter(String id) {
        System.out.println("RecruiterService.getRecruiter.id=" + id);
        return recruiterClient.getRecruiter(id).getBody();
    }
}