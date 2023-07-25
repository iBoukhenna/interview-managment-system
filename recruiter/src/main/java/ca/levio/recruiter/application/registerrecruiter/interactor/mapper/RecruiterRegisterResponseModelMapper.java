package ca.levio.recruiter.application.registerrecruiter.interactor.mapper;

import org.mapstruct.Mapper;

import ca.levio.recruiter.application.registerrecruiter.model.data.RecruiterRegisterDataModel;
import ca.levio.recruiter.application.registerrecruiter.model.response.RecruiterRegisterResponseModel;

@Mapper(componentModel = "spring")
public abstract class RecruiterRegisterResponseModelMapper {

    public abstract RecruiterRegisterResponseModel dataModelToResponseModel(RecruiterRegisterDataModel recruiterRegisterDataModel);
}
