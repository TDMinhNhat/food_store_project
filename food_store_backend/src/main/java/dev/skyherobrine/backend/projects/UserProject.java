package dev.skyherobrine.backend.projects;

import dev.skyherobrine.backend.models.oracle.Address;
import dev.skyherobrine.backend.models.oracle.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UserProject {
    private String userId;
    private String firstName;
    private String lastName;
    private Boolean sex;
    private LocalDate birthDate;
    private String phoneNumber;
    private String image;
    private String email;
    private UserRole role;
    private LocalDateTime createdAt;
}
