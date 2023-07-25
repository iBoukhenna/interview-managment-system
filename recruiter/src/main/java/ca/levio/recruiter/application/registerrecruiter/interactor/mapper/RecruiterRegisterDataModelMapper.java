package ca.levio.recruiter.application.registerrecruiter.interactor.mapper;

import org.mapstruct.Mapper;

import ca.levio.recruiter.application.registerrecruiter.model.data.RecruiterRegisterDataModel;
import ca.levio.recruiter.domain.IRecruiterEntity;

@Mapper(componentModel = "spring")
public abstract class RecruiterRegisterDataModelMapper {

    public abstract RecruiterRegisterDataModel entityToDataModel(IRecruiterEntity iRecruiterEntity);
}
