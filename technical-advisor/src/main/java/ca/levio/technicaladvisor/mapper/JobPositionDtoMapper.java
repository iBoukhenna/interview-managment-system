package ca.levio.technicaladvisor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import ca.levio.technicaladvisor.dto.JobPositionDto;
import ca.levio.technicaladvisor.model.JobPosition;
import ca.levio.technicaladvisor.service.JobPositionService;

@Mapper(componentModel = "spring")
public abstract class JobPositionDtoMapper {
    
    @Autowired
    protected JobPositionService jobPositionService;

    public abstract JobPositionDto jobPositionToJobPositionDto(JobPosition jobPosition);

    @Mapping(target = "hierarchicalLevel", expression = "java(jobPositionService.getJobPosition(jobPositionDto.getJobPositionLabel()).getHierarchicalLevel())")
    @Mapping(target = "hierarchicalLine", expression = "java(jobPositionService.getJobPosition(jobPositionDto.getJobPositionLabel()).getHierarchicalLine())")
    @Mapping(target = "lineOfBusiness", expression = "java(jobPositionService.getJobPosition(jobPositionDto.getJobPositionLabel()).getLineOfBusiness())")
    public abstract JobPosition jobPositionDtoToJobPosition(JobPositionDto jobPositionDto);

}
