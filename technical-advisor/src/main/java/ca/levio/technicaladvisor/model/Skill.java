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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String jobPosition;

    private String lineOfBusiness;

    private LevelOfExpertise levelOfExpertise;
    
}
