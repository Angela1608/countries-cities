package com.countries.cities.dto.request;

import com.countries.cities.lib.FieldsMatch;
import com.countries.cities.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Set;
import lombok.Data;

@FieldsMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
@Data
public class UserRegistrationRequestDto {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?!\\s).*$";

    @Email
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = PASSWORD_PATTERN,
            message = "Password must contain at least one digit, one lowercase "
                    + "and one uppercase letter, one special character, and no spaces")
    private String password;

    private String repeatPassword;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    private Set<Role> roles;

}
