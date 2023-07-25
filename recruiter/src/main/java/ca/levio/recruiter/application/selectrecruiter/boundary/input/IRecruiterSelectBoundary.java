package ca.levio.recruiter.application.selectrecruiter.boundary.input;

import java.util.UUID;

import ca.levio.recruiter.application.selectrecruiter.exception.RecruiterSelectCustomException;
import ca.levio.recruiter.application.selectrecruiter.model.response.RecruiterSelectResponseModel;

public interface IRecruiterSelectBoundary {
    RecruiterSelectResponseModel getRecruiterById(UUID id) throws RecruiterSelectCustomException;
}
