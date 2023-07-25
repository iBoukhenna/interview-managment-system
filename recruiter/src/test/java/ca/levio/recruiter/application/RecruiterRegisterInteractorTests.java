package ca.levio.recruiter.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import ca.levio.recruiter.domain.RecruiterEntity;
import ca.levio.recruiter.application.registerrecruiter.boundary.output.IRecruiterRegisterGateway;
import ca.levio.recruiter.application.registerrecruiter.exception.RecruiterRegisterCustomException;
import ca.levio.recruiter.application.registerrecruiter.interactor.RecruiterRegisterInteractor;
import ca.levio.recruiter.application.registerrecruiter.interactor.mapper.RecruiterRegisterResponseModelMapper;
import ca.levio.recruiter.application.registerrecruiter.interactor.mapper.RecruiterRegisterDataModelMapper;
import ca.levio.recruiter.application.registerrecruiter.model.data.RecruiterRegisterDataModel;
import ca.levio.recruiter.application.registerrecruiter.model.request.RecruiterRegisterRequestModel;
import ca.levio.recruiter.application.registerrecruiter.model.response.RecruiterRegisterResponseModel;
import ca.levio.recruiter.application.registerrecruiter.presenter.IRecruiterRegisterPresenter;
import ca.levio.recruiter.domain.IRecruiterEntity;
import ca.levio.recruiter.domain.factories.IRecruiterEntityFactory;

@SpringBootTest
public class RecruiterRegisterInteractorTests {

    IRecruiterRegisterPresenter mockedRecruiterPresenter;
    IRecruiterEntityFactory mockedRecruiterEntityFactory;
    IRecruiterRegisterGateway mockedRecruiterRegisterGateway;
    RecruiterRegisterResponseModelMapper mockedRecruiterRegisterResponseModelMapper;
    RecruiterRegisterDataModelMapper mockedRecruiterRegisterDataModelMapper;


    @BeforeEach
    void setup(){
        mockedRecruiterPresenter = Mockito.mock(IRecruiterRegisterPresenter.class);
        mockedRecruiterEntityFactory = Mockito.mock(IRecruiterEntityFactory.class);
        mockedRecruiterRegisterGateway = Mockito.mock(IRecruiterRegisterGateway.class);
        mockedRecruiterRegisterResponseModelMapper = Mockito.mock(RecruiterRegisterResponseModelMapper.class);
        mockedRecruiterRegisterDataModelMapper = Mockito.mock(RecruiterRegisterDataModelMapper.class);
    }

    void givenValidRectuierProperties_whenCreate_thenSaveItAndPrepareSuccessView() throws RecruiterRegisterCustomException {
        // ARRANGE
        RecruiterRegisterRequestModel requestModel = new RecruiterRegisterRequestModel("ValidTestName", "Test@gmail.com");
        LocalDateTime creationTime = LocalDateTime.now();
        RecruiterEntity recruiter = new RecruiterEntity(requestModel.getName(), requestModel.getEmail());
        RecruiterRegisterDataModel recruiterRegisterDataModel = new RecruiterRegisterDataModel(UUID.randomUUID(), recruiter.getName(), recruiter.getEmail(), creationTime);
        RecruiterRegisterResponseModel responseModel = new RecruiterRegisterResponseModel(recruiterRegisterDataModel.getId(), recruiter.getName(), recruiter.getEmail(), String.valueOf(creationTime));
        RecruiterRegisterResponseModel finalResponseModel = new RecruiterRegisterResponseModel(responseModel.getId(), recruiter.getName(), recruiter.getEmail(), responseModel.getCreationTime());

        Mockito.when(mockedRecruiterRegisterGateway.existsByEmail(requestModel.getEmail())).thenReturn(false);
        Mockito.when(mockedRecruiterEntityFactory.create(requestModel.getName(), requestModel.getEmail())).thenReturn(recruiter);
        Mockito.when(mockedRecruiterPresenter.prepareSuccessView(responseModel)).thenReturn(finalResponseModel);

        RecruiterRegisterInteractor interactor = new RecruiterRegisterInteractor(mockedRecruiterPresenter, mockedRecruiterEntityFactory, mockedRecruiterRegisterGateway, mockedRecruiterRegisterResponseModelMapper, mockedRecruiterRegisterDataModelMapper);

        // ACT
        RecruiterRegisterResponseModel verifyResponseModel = interactor.create(requestModel);

        // ASSERT
        Assertions.assertEquals("2022-11-16", finalResponseModel.getCreationTime());

        Mockito.verify(mockedRecruiterRegisterGateway, Mockito.times(1)).save(recruiterRegisterDataModel);
        Mockito.verify(mockedRecruiterRegisterGateway, Mockito.times(1)).existsByEmail(requestModel.getEmail());
        Mockito.verify(mockedRecruiterPresenter, Mockito.times(1)).prepareSuccessView(responseModel);
        Assertions.assertEquals(finalResponseModel.getId(), verifyResponseModel.getId());
        Assertions.assertEquals(finalResponseModel.getName(), verifyResponseModel.getName());
        Assertions.assertEquals(finalResponseModel.getEmail(), verifyResponseModel.getEmail());
        Assertions.assertEquals("2022-11-16", verifyResponseModel.getCreationTime());

    }

    @Test
    void givenExistingRecruiter_whenCreate_thenPrepareFailView_withInvalidNameException() throws RecruiterRegisterCustomException {
        // ARRANGE
        RecruiterRegisterRequestModel requestModel = new RecruiterRegisterRequestModel("123", "Test description.com");

        long timestmap = 1668617824L;
        RecruiterEntity recruiter = new RecruiterEntity("123", "Some Description.com");
        Mockito.when(mockedRecruiterRegisterGateway.existsByEmail(requestModel.getEmail())).thenReturn(false);
        Mockito.when(mockedRecruiterEntityFactory.create(requestModel.getName(), requestModel.getEmail())).thenReturn(recruiter);
        String expectedMessage = "Name 123 is not valid";
        RecruiterRegisterCustomException invalidNameException = new RecruiterRegisterCustomException(expectedMessage);
        Mockito.when(mockedRecruiterPresenter.prepareFailView(invalidNameException)).thenThrow(invalidNameException);
        Exception failViewException = Assertions.assertThrows(RecruiterRegisterCustomException.class, () -> mockedRecruiterPresenter.prepareFailView(invalidNameException));

        // ACT
        RecruiterRegisterInteractor interactor = new RecruiterRegisterInteractor(mockedRecruiterPresenter, mockedRecruiterEntityFactory, mockedRecruiterRegisterGateway, mockedRecruiterRegisterResponseModelMapper, mockedRecruiterRegisterDataModelMapper);
        interactor.create(requestModel);


        // ASSERT
        Assertions.assertTrue(failViewException.getMessage().contains(expectedMessage));
        Mockito.verify(mockedRecruiterEntityFactory, Mockito.times(1)).create(requestModel.getName(), requestModel.getEmail());
        Mockito.verify(mockedRecruiterRegisterGateway, Mockito.times(1)).existsByEmail(requestModel.getEmail());
        Mockito.verify(mockedRecruiterPresenter, Mockito.times(1)).prepareFailView(invalidNameException);
    }


    @Test
    void given123Name_whenCreate_thenPrepareFailView_withAlreadyContainsException() throws RecruiterRegisterCustomException {

        // ARRANGE
        RecruiterRegisterRequestModel requestModel = new RecruiterRegisterRequestModel("123", "Test description");

        RecruiterEntity recruiter = new RecruiterEntity("123", "Some Description");

        Mockito.when(mockedRecruiterRegisterGateway.existsByEmail(requestModel.getEmail())).thenReturn(true);
        Mockito.when(mockedRecruiterEntityFactory.create(requestModel.getName(), requestModel.getEmail())).thenReturn(recruiter);
        String expectedMessage = "Recruiter with id 123 already in database";
        RecruiterRegisterCustomException customRecruiterException = new RecruiterRegisterCustomException(expectedMessage);
        Mockito.when(mockedRecruiterPresenter.prepareFailView(customRecruiterException)).thenThrow(customRecruiterException);
        Exception failViewException = Assertions.assertThrows(RecruiterRegisterCustomException.class, () -> mockedRecruiterPresenter.prepareFailView(customRecruiterException));

        // ACT
        RecruiterRegisterInteractor interactor = new RecruiterRegisterInteractor(mockedRecruiterPresenter, mockedRecruiterEntityFactory, mockedRecruiterRegisterGateway, mockedRecruiterRegisterResponseModelMapper, mockedRecruiterRegisterDataModelMapper);
        interactor.create(requestModel);

        // ASSERT
        Assertions.assertTrue(failViewException.getMessage().contains(expectedMessage));
        Mockito.verify(mockedRecruiterRegisterGateway, Mockito.times(1)).existsByEmail(requestModel.getEmail());
        Mockito.verify(mockedRecruiterPresenter, Mockito.times(1)).prepareFailView(customRecruiterException);

    }

    @Test
    void givenIbrahimRecruiterAndValidEmail_whenCreate_thenSaveItAndPrepareSuccessView() throws RecruiterRegisterCustomException {
        IRecruiterEntity recruiter = new RecruiterEntity("ibrahim", "ibrahim@gmail.com");
        RecruiterRegisterRequestModel recruiterRequestModel = new RecruiterRegisterRequestModel(recruiter.getName(), recruiter.getEmail());
        when(mockedRecruiterEntityFactory.create(anyString(), anyString()))
            .thenReturn(new RecruiterEntity(recruiter.getName(), recruiter.getEmail()));

        RecruiterRegisterInteractor interactor = new RecruiterRegisterInteractor(mockedRecruiterPresenter, mockedRecruiterEntityFactory, mockedRecruiterRegisterGateway, mockedRecruiterRegisterResponseModelMapper, mockedRecruiterRegisterDataModelMapper);
        interactor.create(recruiterRequestModel);

        verify(mockedRecruiterRegisterGateway, times(1)).save(any(RecruiterRegisterDataModel.class));
        verify(mockedRecruiterPresenter, times(1)).prepareSuccessView(any(RecruiterRegisterResponseModel.class));
    }
}
