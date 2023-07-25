package ca.levio.recruiter.domain;

import java.util.regex.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RecruiterEntity implements IRecruiterEntity {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final int MINIMUM_CHARACTER_LIMIT = 5;

    private String name;
    private String email;

    @Override
    public boolean emailIsValid() {
        return email != null && EMAIL_PATTERN.matcher(email).matches(); 
    }

    @Override
    public boolean nameIsValid() {
        return name != null && name.length() >= MINIMUM_CHARACTER_LIMIT;
    }
}
