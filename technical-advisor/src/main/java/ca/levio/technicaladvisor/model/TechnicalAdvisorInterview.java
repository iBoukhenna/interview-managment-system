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
public class TechnicalAdvisorInterview {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String interview;

    @ManyToOne(fetch = FetchType.EAGER)
    private TechnicalAdvisor technicalAdvisor;
}
