package ca.levio.recruiter.application.registerrecruiter.interactor;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ca.levio.recruiter.application.registerrecruiter.boundary.input.IRecruiterRegisterBoundary;
import ca.levio.recruiter.application.registerrecruiter.boundary.output.IRecruiterRegisterGateway;
import ca.levio.recruiter.application.registerrecruiter.exception.RecruiterRegisterCustomException;
import ca.levio.recruiter.application.registerrecruiter.interactor.mapper.RecruiterRegisterDataModelMapper;
import ca.levio.recruiter.application.registerrecruiter.interactor.mapper.RecruiterRegisterResponseModelMapper;
import ca.levio.recruiter.application.registerrecruiter.model.data.RecruiterRegisterDataModel;
import ca.levio.recruiter.application.registerrecruiter.model.request.RecruiterRegisterRequestModel;
import ca.levio.recruiter.application.registerrecruiter.model.response.RecruiterRegisterResponseModel;
import ca.levio.recruiter.application.registerrecruiter.presenter.IRecruiterRegisterPresenter;
import ca.levio.recruiter.domain.IRecruiterEntity;
import ca.levio.recruiter.domain.factories.IRecruiterEntityFactory;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RecruiterRegisterInteractor implements IRecruiterRegisterBoundary {

    final IRecruiterRegisterPresenter recruiterPresenter;
    final IRecruiterEntityFactory recruiterEntityFactory;
    final IRecruiterRegisterGateway recruiterRegisterGateway;
    final RecruiterRegisterResponseModelMapper recruiterRegisterResponseModelMapper;
    final RecruiterRegisterDataModelMapper recruiterRegisterDataModelMapper;

    @Override
    public RecruiterRegisterResponseModel create(RecruiterRegisterRequestModel recruiterRegisterRequestModel) throws RecruiterRegisterCustomException {
        if (recruiterRegisterGateway.existsByEmail(recruiterRegisterRequestModel.getEmail())) {
            return recruiterPresenter.prepareFailView(new RecruiterRegisterCustomException("Recruiter already exists."));
        }
        IRecruiterEntity iRecruiterEntity = recruiterEntityFactory.create(recruiterRegisterRequestModel.getName(), recruiterRegisterRequestModel.getEmail());

        if (!iRecruiterEntity.emailIsValid()) {
            return recruiterPresenter.prepareFailView(new RecruiterRegisterCustomException("Recruiter email must be valide."));
        }

        if (!iRecruiterEntity.nameIsValid()) {
            return recruiterPresenter.prepareFailView(new RecruiterRegisterCustomException("Recruiter name must be valide."));
        }

        RecruiterRegisterDataModel recruiterRegisterDataModel = recruiterRegisterDataModelMapper.entityToDataModel(iRecruiterEntity);
        recruiterRegisterDataModel.setId(UUID.randomUUID());
        recruiterRegisterDataModel.setCreationTime(LocalDateTime.now());
        recruiterRegisterDataModel = recruiterRegisterGateway.save(recruiterRegisterDataModel);

        RecruiterRegisterResponseModel recruiterRegisterResponseModel = recruiterRegisterResponseModelMapper.dataModelToResponseModel(recruiterRegisterDataModel);
        return recruiterPresenter.prepareSuccessView(recruiterRegisterResponseModel);
    }
}
