package ca.levio.mailmaker.maildtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class InterviewAcceptedMailDataRequestDto extends MailDataRequestDto {
    private String technicalAdvisorName;
    private String linkInterviewDetail;
    private String linkRecruiterDetail;
}
