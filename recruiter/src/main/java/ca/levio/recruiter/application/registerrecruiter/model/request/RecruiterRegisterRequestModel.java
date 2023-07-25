package ca.levio.recruiter.application.registerrecruiter.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RecruiterRegisterRequestModel {
    String name;
    String email;
}
