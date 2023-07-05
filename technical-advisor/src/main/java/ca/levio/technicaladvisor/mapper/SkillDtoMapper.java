package ca.levio.technicaladvisor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import ca.levio.technicaladvisor.dto.SkillDto;
import ca.levio.technicaladvisor.model.Skill;
import ca.levio.technicaladvisor.service.JobPositionService;

@Mapper(componentModel = "spring")
public abstract class SkillDtoMapper {

    @Autowired
    protected JobPositionService jobPositionService;

    @Mapping(target = "jobPositionLabel", source = "skill.jobPosition.jobPositionLabel")
    public abstract SkillDto skillToSkillDto(Skill skill);

    @Mapping(target = "jobPosition", expression = "java(jobPositionService.getJobPosition(skillDto.getJobPositionLabel()))")
    public abstract Skill skillDtoToSkill(SkillDto skillDto);
}
