package ca.levio.recruiter.adapter.presenter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import ca.levio.recruiter.application.registerrecruiter.exception.RecruiterRegisterCustomException;
import ca.levio.recruiter.application.registerrecruiter.model.response.RecruiterRegisterResponseModel;
import ca.levio.recruiter.application.registerrecruiter.presenter.IRecruiterRegisterPresenter;
import ca.levio.recruiter.application.selectrecruiter.exception.RecruiterSelectCustomException;
import ca.levio.recruiter.application.selectrecruiter.model.response.RecruiterSelectResponseModel;
import ca.levio.recruiter.application.selectrecruiter.presenter.IRecruiterSelectPresenter;

@Component
public class RecruiterPresenter implements IRecruiterRegisterPresenter, IRecruiterSelectPresenter {

    @Override
    public RecruiterRegisterResponseModel prepareFailView(RecruiterRegisterCustomException recruiterCustomException) throws RecruiterRegisterCustomException {
        throw recruiterCustomException;
    }

    @Override
    public RecruiterRegisterResponseModel prepareSuccessView(RecruiterRegisterResponseModel recruiterResponseModel) {
        LocalDateTime responseTime = LocalDateTime.parse(recruiterResponseModel.getCreationTime());
        recruiterResponseModel.setCreationTime(responseTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
        return recruiterResponseModel;
    }

    @Override
    public RecruiterSelectResponseModel prepareFailView(RecruiterSelectCustomException recruiterCustomException) throws RecruiterSelectCustomException {
        throw recruiterCustomException;
    }

    @Override
    public RecruiterSelectResponseModel prepareSuccessView(RecruiterSelectResponseModel recruiterResponseModel) {
        LocalDateTime responseTime = LocalDateTime.parse(recruiterResponseModel.getCreationTime());
        recruiterResponseModel.setCreationTime(responseTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
        return recruiterResponseModel;
    }
}
