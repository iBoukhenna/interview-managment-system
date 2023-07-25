package ca.levio.recruiter.adapter.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.levio.recruiter.adapter.gateway.model.Recruiter;

@Repository
public interface IRecruiterRepository extends JpaRepository<Recruiter, UUID> {

    boolean existsByEmail(String email);
}