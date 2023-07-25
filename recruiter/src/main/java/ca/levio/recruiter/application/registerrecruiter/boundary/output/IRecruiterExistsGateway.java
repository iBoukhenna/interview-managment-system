package ca.levio.recruiter.application.registerrecruiter.boundary.output;

public interface IRecruiterExistsGateway {

    boolean existsByEmail(String email);
}
