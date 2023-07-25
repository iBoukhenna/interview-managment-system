package ca.levio.recruiter.adapter.gateway.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recruiters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recruiter {
    @Id
    private UUID id;
    private String name;
    private String email;
    private LocalDateTime creationTime;
}
