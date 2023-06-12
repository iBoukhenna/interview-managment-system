package ca.levio.technicaladvisor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ca.levio.technicaladvisor.enums.LevelOfExpertise;
import jakarta.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Skill {
    @Id
    @SequenceGenerator(
            name = "skill_id_sequence",
            sequenceName = "skill_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "skill_id_sequence"
    )
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private JobPosition jobPosition;

    private LevelOfExpertise levelOfExpertise;
    
}
