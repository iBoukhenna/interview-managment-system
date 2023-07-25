package ca.levio.recruiter.adapter.gateway.mapper;

import org.mapstruct.Mapper;

import ca.levio.recruiter.adapter.gateway.model.Recruiter;
import ca.levio.recruiter.application.selectrecruiter.model.data.RecruiterSelectDataModel;

@Mapper(componentModel = "spring")
public abstract class RecruiterSelectModelMapper {

    public abstract RecruiterSelectDataModel recruiterToDataModel(Recruiter recruiter);

    public abstract Recruiter dataModelToRecruiter(RecruiterSelectDataModel recruiterSelectDataModel);
}
