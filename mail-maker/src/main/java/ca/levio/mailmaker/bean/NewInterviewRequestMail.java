package ca.levio.mailmaker.bean;
//Todo : pourquoi nommer ton package bean ?
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class NewInterviewRequestMail {
    private String to;
    private String technicalAdvisorName;
    private String linkInterviewDetail;
    private String linkRejectInterview;
    private String linkAcceptInterview;
}
