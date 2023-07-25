package ca.levio.recruiter.application.registerrecruiter.boundary.output;

import ca.levio.recruiter.application.registerrecruiter.model.data.RecruiterRegisterDataModel;

public interface IRecruiterRegisterGateway extends IRecruiterExistsGateway {
    RecruiterRegisterDataModel save(RecruiterRegisterDataModel recruiterRegisterDataModel);
}
