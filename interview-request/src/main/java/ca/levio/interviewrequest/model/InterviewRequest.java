package ca.levio.interviewrequest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ca.levio.interviewrequest.enums.StatusOfRequest;
import jakarta.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InterviewRequest {
    @Id
    @SequenceGenerator(
            name = "interview_request_id_sequence",
            sequenceName = "interview_request_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "interview_request_id_sequence"
    )
    private Integer id;

    private Integer Interview;

    private String technicalAdvisor;

    private StatusOfRequest statusOfRequest;
    private String motif;

}
