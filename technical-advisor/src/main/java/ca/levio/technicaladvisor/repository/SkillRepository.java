package ca.levio.technicaladvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.levio.technicaladvisor.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, String> {
}
