package ca.levio.recruiter.application.selectrecruiter.interactor.mapper;

import org.mapstruct.Mapper;

import ca.levio.recruiter.application.selectrecruiter.model.data.RecruiterSelectDataModel;
import ca.levio.recruiter.application.selectrecruiter.model.response.RecruiterSelectResponseModel;

@Mapper(componentModel = "spring")
public abstract class RecruiterSelectResponseModelMapper {

    public abstract RecruiterSelectResponseModel dataModelToResponseModel(RecruiterSelectDataModel recruiterSelectDataModel);
}
