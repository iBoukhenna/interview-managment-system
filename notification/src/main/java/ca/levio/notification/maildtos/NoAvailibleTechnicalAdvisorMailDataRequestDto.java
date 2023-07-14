package ca.levio.notification.maildtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class NoAvailibleTechnicalAdvisorMailDataRequestDto extends MailDataRequestDto {
    private String RecruiterName;
    private String linkInterviewDetail;
}
