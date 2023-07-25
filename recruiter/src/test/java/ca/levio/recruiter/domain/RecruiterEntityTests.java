package ca.levio.recruiter.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RecruiterEntityTests {
    
    @Test
    void givenInvalidEmail_whenEmailIsNotValid_thenIsFalse() {
        IRecruiterEntity recruiter = new RecruiterEntity("Ibrahim", "ibrahim@gmail@com");
        assertEquals(false, recruiter.emailIsValid());
    }

    @Test
    void givenValidEmail_whenEmailIsValid_thenIsTrue() {
        IRecruiterEntity recruiter = new RecruiterEntity("Ibrahim", "ibrahim@gmail.com");
        assertEquals(true, recruiter.emailIsValid());
    }

    @Test
    void givenInvalidName_whenNameIsNotValid_thenIsFalse() {
        IRecruiterEntity recruiter = new RecruiterEntity("Ibra", "ibrahim@gmail.com");
        assertEquals(false, recruiter.nameIsValid());
    }

    @Test
    void givenValidName_whenNameIsValid_thenIsTrue() {
        IRecruiterEntity recruiter = new RecruiterEntity("Ibrahim", "ibrahim@gmail.com");
        assertEquals(true, recruiter.nameIsValid());
    }
}
