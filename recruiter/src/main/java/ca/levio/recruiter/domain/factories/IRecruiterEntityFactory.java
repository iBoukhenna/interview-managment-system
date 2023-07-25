package ca.levio.recruiter.domain.factories;

import ca.levio.recruiter.domain.IRecruiterEntity;

public interface IRecruiterEntityFactory {
    IRecruiterEntity create(String name, String email);
}
