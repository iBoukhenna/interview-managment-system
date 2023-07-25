package ca.levio.recruiter.application.selectrecruiter.presenter;

import ca.levio.recruiter.application.selectrecruiter.exception.RecruiterSelectCustomException;
import ca.levio.recruiter.application.selectrecruiter.model.response.RecruiterSelectResponseModel;

public interface IRecruiterSelectPresenter {
    RecruiterSelectResponseModel prepareSuccessView(RecruiterSelectResponseModel recruiterResponseModel);

    RecruiterSelectResponseModel prepareFailView(RecruiterSelectCustomException recruiterCustomException) throws RecruiterSelectCustomException;
}
