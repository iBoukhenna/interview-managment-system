package ca.levio.technicaladvisor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobPosition {

    @Id
    @SequenceGenerator(
            name = "jib_position_id_sequence",
            sequenceName = "job_position_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "job_position_id_sequence"
    )
    private Integer id;

    private String label;

    @ManyToOne(fetch = FetchType.EAGER)
    private LineOfBusiness lineOfBusiness;
}
