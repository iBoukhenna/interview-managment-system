package ca.levio.recruiter.application.selectrecruiter.boundary.output;

import java.util.UUID;

import ca.levio.recruiter.application.selectrecruiter.model.data.RecruiterSelectDataModel;

public interface IRecruiterSelectGateway {
    RecruiterSelectDataModel getRecruiterById(UUID id);
}
