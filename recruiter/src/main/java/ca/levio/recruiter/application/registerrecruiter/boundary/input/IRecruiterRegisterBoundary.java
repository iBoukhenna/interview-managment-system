package ca.levio.recruiter.application.registerrecruiter.boundary.input;

import ca.levio.recruiter.application.registerrecruiter.exception.RecruiterRegisterCustomException;
import ca.levio.recruiter.application.registerrecruiter.model.request.RecruiterRegisterRequestModel;
import ca.levio.recruiter.application.registerrecruiter.model.response.RecruiterRegisterResponseModel;

public interface IRecruiterRegisterBoundary {
    RecruiterRegisterResponseModel create(RecruiterRegisterRequestModel recruiterRequestModel) throws RecruiterRegisterCustomException;
}
