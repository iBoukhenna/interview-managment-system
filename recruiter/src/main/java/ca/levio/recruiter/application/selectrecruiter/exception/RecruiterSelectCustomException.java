package ca.levio.recruiter.application.selectrecruiter.exception;

public class RecruiterSelectCustomException extends Exception {

    public RecruiterSelectCustomException() {
    }

    public RecruiterSelectCustomException(String message) {
        super(message);
    }

    public RecruiterSelectCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}