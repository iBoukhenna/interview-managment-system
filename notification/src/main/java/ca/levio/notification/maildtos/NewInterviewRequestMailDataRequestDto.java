package ca.levio.notification.maildtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class NewInterviewRequestMailDataRequestDto extends MailDataRequestDto {
    private String technicalAdvisorName;
    private String linkInterviewDetail;
    private String linkRejectInterview;
    private String linkAcceptInterview;
}
