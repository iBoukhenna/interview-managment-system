package ca.levio.recruiter.domain;

public interface IRecruiterEntity {
    String getName();
    String getEmail();
    boolean emailIsValid();
    boolean nameIsValid();
}
