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
public class LineOfBusiness {
    @Id
    @SequenceGenerator(
            name = "line_of_business_id_sequence",
            sequenceName = "line_of_business_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "line_of_business_id_sequence"
    )
    private Integer id;

    private String label;
}
