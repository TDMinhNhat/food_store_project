package dev.skyherobrine.backend.dtos;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link dev.skyherobrine.backend.models.oracle.User}
 */
public record UserDto(
        @Size(message = "Maximum length for 'firstName' is 50 characters", max = 50)
        @Pattern(message = "The 'firstName' can only contain alphabets and underscores", regexp = "^[a-zA-Z_]+$")
        @NotBlank(message = "The property 'firstName' is required.")
        String firstName,

        @Size(message = "Maximum length for 'lastName' is 50 characters.", max = 50)
        @Pattern(message = "The 'lastName' can only contain alphabets and underscores", regexp = "^[a-zA-Z_]+$")
        @NotBlank(message = "The property 'lastName' is required")
        String lastName,

        @NotNull(message = "The property 'sex' is required.")
        Boolean sex,

        @NotNull(message = "The property 'birthDate' is required")
        @PastOrPresent(message = "Time for 'birthDate' must be past or now")
        LocalDate birthDate,

        @Size(message = "Maximum and minimum length for 'phoneNumber' are 25 and 10 characters.", min = 10, max = 25)
        @Pattern(message = "The 'phoneNumber' can only contain numbers and country code only.", regexp = "^[+0-9]+$")
        @NotBlank(message = "The property 'phoneNumber' is required")
        String phoneNumber,

        AddressDto address,

        @Size(message = "Maximum length for 'email' is 200 characters.", max = 200)
        @Email(message = "The email isn't valid format.", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        @NotBlank(message = "The property 'email' is required.")
        String email,

        @Size(min = 8, max = 200, message = "Minimum and maximum length for 'password' are) 8 and 200 characters.")
        @NotBlank(message = "The property 'password' is required.")
        String password
) implements Serializable {

}