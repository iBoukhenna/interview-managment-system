package ca.levio.mailmaker.maildtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class InterviewAssignedMailDataRequestDto extends MailDataRequestDto {
    private String RecruiterName;
    private String linkInterviewDetail;
    private String linkTechnicalAdvisorDetail;
}
