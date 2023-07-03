package ca.levio.technicaladvisor.mapper;

import org.mapstruct.Mapper;

import ca.levio.technicaladvisor.dto.SkillDto;
import ca.levio.technicaladvisor.model.Skill;

@Mapper(componentModel = "spring")
public abstract class SkillDtoMapper {

    public abstract SkillDto skillToSkillDto(Skill skill);

    public abstract Skill skillDtoToSkill(SkillDto skillDto);

}
