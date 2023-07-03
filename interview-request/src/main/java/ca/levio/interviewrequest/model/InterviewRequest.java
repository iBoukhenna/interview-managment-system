package ca.levio.interviewrequest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import ca.levio.interviewrequest.enums.StatusOfRequest;
import jakarta.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InterviewRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String interview;

    private String technicalAdvisor;

    private String technicalAdvisorEmail;

    private String technicalAdvisorName;

    private LocalDateTime acceptedAt;

    private String recruiter;

    private String recruiterName;

    private String recruiterEmail;

    @Builder.Default
    private StatusOfRequest statusOfRequest = StatusOfRequest.OPENED;
    private String motif;

}
