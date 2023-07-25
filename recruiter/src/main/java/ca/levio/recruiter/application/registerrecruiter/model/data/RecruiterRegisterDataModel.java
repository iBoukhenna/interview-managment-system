package ca.levio.recruiter.application.registerrecruiter.model.data;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RecruiterRegisterDataModel {
    UUID id;
    String name;
    String email;
    LocalDateTime creationTime;
}
