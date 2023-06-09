package ca.levio.notification.maildtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class InterviewDeclinedMailDataRequestDto extends MailDataRequestDto {
    private String technicalAdvisorName;
    private String linkInterviewDetail;
}
