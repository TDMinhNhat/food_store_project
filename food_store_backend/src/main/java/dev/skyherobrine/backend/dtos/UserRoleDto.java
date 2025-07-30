package dev.skyherobrine.backend.dtos;

import dev.skyherobrine.backend.models.oracle.UserRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record UserRoleDto(
        @NotNull(message = "The property 'roleName' is required.")
        @Size(max = 100, message = "Maximum length for 'roleName' is 100 characters.")
        @Pattern(regexp = "^[a-zA-Z_]+$", message = "The 'roleName' can only contain alphabet characters and underscores.")
        String roleName,
        @Size(max = 300, message = "Maximum length for 'description' is 300 characters.")
        String description
) implements Serializable {

    public UserRole toObject() {
        return new UserRole(roleName, description);
    }
}
