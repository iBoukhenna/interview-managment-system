package ca.levio.technicaladvisor.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import ca.levio.technicaladvisor.dto.SkillDto;
import ca.levio.technicaladvisor.dto.TechnicalAdvisorDto;
import ca.levio.technicaladvisor.model.Skill;
import ca.levio.technicaladvisor.model.TechnicalAdvisor;

@Mapper(componentModel = "spring")
public abstract class TechnicalAdvisorDtoMapper {

    @Autowired
    private SkillDtoMapper skillDtoMapper;

    @Mapping(target = "skillDtos", expression = "java(mapListOfSkills(technicalAdvisor.getSkills()))")
    public abstract TechnicalAdvisorDto technicalAdvisorToTechnicalAdvisorDto(TechnicalAdvisor technicalAdvisor);

    @Mapping(target = "skills", expression = "java(mapListOfSkillDtos(technicalAdvisorDto.getSkillDtos()))")
    public abstract TechnicalAdvisor technicalAdvisorDtoToTechnicalAdvisor(TechnicalAdvisorDto technicalAdvisorDto);

    public List<SkillDto> mapListOfSkills(Collection<Skill> skills) {
         return skills.stream()
                .map(skillDtoMapper::skillToSkillDto)
                .collect(Collectors.toList());
    }

    public Collection<Skill> mapListOfSkillDtos(List<SkillDto> skillDtos) {
        return skillDtos.stream()
                .map(skillDtoMapper::skillDtoToSkill)
                .collect(Collectors.toList());
    }
}
