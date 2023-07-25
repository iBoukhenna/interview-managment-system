package ca.levio.recruiter.application.registerrecruiter.model.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RecruiterRegisterResponseModel {
    UUID id;
    String name;
    String email;
    String creationTime;
}
