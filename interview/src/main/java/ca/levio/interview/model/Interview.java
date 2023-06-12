package ca.levio.interview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ca.levio.interview.enums.LevelOfExpertise;
import ca.levio.interview.enums.StateOfInterview;
import ca.levio.interview.enums.TypeOfInterview;
import jakarta.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Interview {
    @Id
    @SequenceGenerator(
            name = "interview_id_sequence",
            sequenceName = "interview_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "interview_id_sequence"
    )
    private Integer id;

    private String applicant;
    private String recruiter;
    private String jobPosition;

    private StateOfInterview stateOfInterview;
    private LevelOfExpertise levelOfExpertise;
    private TypeOfInterview typeOfInterview;
}
