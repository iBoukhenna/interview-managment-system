package ca.levio.technicaladvisor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TechnicalAdvisor {
    @Id
    @SequenceGenerator(
            name = "technical_advisor_id_sequence",
            sequenceName = "technical_advisor_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "technical_advisor_id_sequence"
    )
    private Integer id;

    private String name;
    
    private String email;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Skill> skills = new ArrayList<>();
    
}
