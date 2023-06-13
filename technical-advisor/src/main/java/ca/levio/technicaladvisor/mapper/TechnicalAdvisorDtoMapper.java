package ca.levio.technicaladvisor.mapper;

import org.mapstruct.Mapper;

import ca.levio.technicaladvisor.dto.TechnicalAdvisorDto;
import ca.levio.technicaladvisor.model.TechnicalAdvisor;

@Mapper(componentModel = "spring")
public abstract class TechnicalAdvisorDtoMapper {

    public abstract TechnicalAdvisorDto TechnicalAdvisorToTechnicalAdvisorDto(TechnicalAdvisor TechnicalAdvisor);

    public abstract TechnicalAdvisor TechnicalAdvisorDtoToTechnicalAdvisor(TechnicalAdvisorDto TechnicalAdvisorDto);

}
