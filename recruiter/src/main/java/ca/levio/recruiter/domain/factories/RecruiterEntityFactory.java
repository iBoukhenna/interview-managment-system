package ca.levio.recruiter.domain.factories;

import ca.levio.recruiter.domain.RecruiterEntity;

import org.springframework.stereotype.Component;

import ca.levio.recruiter.domain.IRecruiterEntity;

@Component
public class RecruiterEntityFactory implements IRecruiterEntityFactory {

    @Override
    public IRecruiterEntity create(String name, String email) {
        return new RecruiterEntity(name, email);
    }
}
