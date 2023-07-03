package ca.levio.technicaladvisor.dto;

import java.util.List;

import lombok.Data;

@Data
public class TechnicalAdvisorDto {

    private String name;
    private String email;
    private List<SkillDto> skills;
}
