package ca.levio.technicaladvisor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ca.levio.technicaladvisor.model.Skill;
import ca.levio.technicaladvisor.repository.SkillRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public Skill getSkill(String skill) {
        Optional<Skill> optionalSkill = skillRepository.findById(skill);
        return optionalSkill.isPresent() ? optionalSkill.get() : null;
    }

    public void saveAllSkills(List<Skill> skills) {
        skillRepository.saveAllAndFlush(skills);
    }

    public void deleteAll() {
        skillRepository.deleteAll();
    }

}
