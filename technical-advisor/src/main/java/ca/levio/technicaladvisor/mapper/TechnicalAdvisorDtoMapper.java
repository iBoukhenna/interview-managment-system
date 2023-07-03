package ca.levio.technicaladvisor.mapper;

import org.mapstruct.Mapper;

import ca.levio.technicaladvisor.dto.TechnicalAdvisorDto;
import ca.levio.technicaladvisor.model.TechnicalAdvisor;

@Mapper(componentModel = "spring")
public abstract class TechnicalAdvisorDtoMapper {

    public abstract TechnicalAdvisorDto technicalAdvisorToTechnicalAdvisorDto(TechnicalAdvisor technicalAdvisor);

    public abstract TechnicalAdvisor technicalAdvisorDtoToTechnicalAdvisor(TechnicalAdvisorDto technicalAdvisorDto);
}
