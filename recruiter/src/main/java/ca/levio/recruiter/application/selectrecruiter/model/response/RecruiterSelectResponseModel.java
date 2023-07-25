package ca.levio.recruiter.application.selectrecruiter.model.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RecruiterSelectResponseModel {
    UUID id;
    String name;
    String email;
    String creationTime;
}
