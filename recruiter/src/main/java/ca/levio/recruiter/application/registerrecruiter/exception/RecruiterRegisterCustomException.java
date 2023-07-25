package ca.levio.recruiter.application.registerrecruiter.exception;

public class RecruiterRegisterCustomException extends Exception {

    public RecruiterRegisterCustomException() {
    }

    public RecruiterRegisterCustomException(String message) {
        super(message);
    }

    public RecruiterRegisterCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}