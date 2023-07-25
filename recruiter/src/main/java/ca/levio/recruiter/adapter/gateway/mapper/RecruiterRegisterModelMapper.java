package ca.levio.recruiter.adapter.gateway.mapper;

import org.mapstruct.Mapper;

import ca.levio.recruiter.adapter.gateway.model.Recruiter;
import ca.levio.recruiter.application.registerrecruiter.model.data.RecruiterRegisterDataModel;

@Mapper(componentModel = "spring")
public abstract class RecruiterRegisterModelMapper {

    public abstract RecruiterRegisterDataModel recruiterToDataModel(Recruiter recruiter);

    public abstract Recruiter dataModelToRecruiter(RecruiterRegisterDataModel recruiterRegisterDataModel);
}
