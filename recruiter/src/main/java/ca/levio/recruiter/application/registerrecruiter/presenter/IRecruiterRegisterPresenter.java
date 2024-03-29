package ca.levio.recruiter.application.registerrecruiter.presenter;

import ca.levio.recruiter.application.registerrecruiter.exception.RecruiterRegisterCustomException;
import ca.levio.recruiter.application.registerrecruiter.model.response.RecruiterRegisterResponseModel;

public interface IRecruiterRegisterPresenter {
    RecruiterRegisterResponseModel prepareSuccessView(RecruiterRegisterResponseModel recruiterRegisterResponseModel);

    RecruiterRegisterResponseModel prepareFailView(RecruiterRegisterCustomException recruiterCustomException) throws RecruiterRegisterCustomException;
}
