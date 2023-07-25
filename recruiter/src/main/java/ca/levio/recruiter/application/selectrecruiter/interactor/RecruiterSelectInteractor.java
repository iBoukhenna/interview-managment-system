package ca.levio.recruiter.application.selectrecruiter.interactor;

import java.util.UUID;

import org.springframework.stereotype.Service;

import ca.levio.recruiter.application.selectrecruiter.boundary.input.IRecruiterSelectBoundary;
import ca.levio.recruiter.application.selectrecruiter.boundary.output.IRecruiterSelectGateway;
import ca.levio.recruiter.application.selectrecruiter.exception.RecruiterSelectCustomException;
import ca.levio.recruiter.application.selectrecruiter.interactor.mapper.RecruiterSelectResponseModelMapper;
import ca.levio.recruiter.application.selectrecruiter.model.data.RecruiterSelectDataModel;
import ca.levio.recruiter.application.selectrecruiter.model.response.RecruiterSelectResponseModel;
import ca.levio.recruiter.application.selectrecruiter.presenter.IRecruiterSelectPresenter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RecruiterSelectInteractor implements IRecruiterSelectBoundary {

    final IRecruiterSelectPresenter recruiterPresenter;
    final IRecruiterSelectGateway recruiterSelectDataSourceGateway;
    final RecruiterSelectResponseModelMapper recruiterSelectResponseModelMapper;

    @Override
    public RecruiterSelectResponseModel getRecruiterById(UUID id) throws RecruiterSelectCustomException {
        RecruiterSelectDataModel recruiterSelectDataModel = recruiterSelectDataSourceGateway.getRecruiterById(id);
        if (recruiterSelectDataModel == null ) {
            return recruiterPresenter.prepareFailView(new RecruiterSelectCustomException("Recruiter not found."));
        }

        RecruiterSelectResponseModel recruiterResponseModel = recruiterSelectResponseModelMapper.dataModelToResponseModel(recruiterSelectDataModel);
        return recruiterPresenter.prepareSuccessView(recruiterResponseModel);
    }
}
