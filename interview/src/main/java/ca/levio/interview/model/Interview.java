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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String applicant;

    private String recruiter;
 
    private String jobPosition;

    @Builder.Default
    private StateOfInterview stateOfInterview = StateOfInterview.OPEN;
    private LevelOfExpertise levelOfExpertise;
    private TypeOfInterview typeOfInterview;
}
