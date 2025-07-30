package dev.skyherobrine.backend.controllers.admins.impls;

import dev.skyherobrine.backend.controllers.admins.IManagement;
import dev.skyherobrine.backend.dtos.UserDto;
import dev.skyherobrine.backend.enums.UserStatus;
import dev.skyherobrine.backend.models.oracle.User;
import dev.skyherobrine.backend.models.oracle.UserRole;
import dev.skyherobrine.backend.repositories.oracle.UserRepository;
import dev.skyherobrine.backend.repositories.oracle.UserRoleRepository;
import dev.skyherobrine.backend.services.admins.UserManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins/user")
@Tag(name = "User Management (Admin)", description = "Management of users in the system")
@RequiredArgsConstructor
public class UserManagementController implements IManagement<UserDto, Long> {

    private final UserRepository userRepository;
    private final UserManagementService userManagementService;
    private final UserRoleRepository userRoleRepository;

    @PostMapping
    @Operation(summary = "Add a new user into database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User added successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            }),
            @ApiResponse(responseCode = "400", description = "Validation failed of the form data"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    @Override
    public ResponseEntity<Object> add(@Valid @RequestBody UserDto dto) {
        return ResponseEntity.ok(userManagementService.addUser(dto));
    }

    @Override
    public ResponseEntity<Object> addMany(List<UserDto> list) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(Long id, UserDto dto) {
        return null;
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update user information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            }),
            @ApiResponse(responseCode = "400", description = "Validation failed of the form data"),
            @ApiResponse(responseCode = "404", description = "The userId wasn't found in the database"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    public ResponseEntity<Object> update(
            @PathVariable("userId") String userId,
            @Valid @RequestBody UserDto dto) {
        return ResponseEntity.ok(userManagementService.updateUser(userId, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user using ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            }),
            @ApiResponse(responseCode = "404", description = "The userId wasn't found in the database"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    @Override
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The userId '" + id + "' wasn't found!"));
        user.setStatus(UserStatus.DELETED);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @Override
    public ResponseEntity<Object> getById(Long id) {
        return null;
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user by userId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            }),
            @ApiResponse(responseCode = "404", description = "The userId wasn't found in the database"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    public ResponseEntity<Object> getByUserId(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userRepository.findByUserId(userId));
    }

    @GetMapping
    @Operation(summary = "Get all users in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            }),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    @Override
    public ResponseEntity<Object> getAll() throws Exception {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/page")
    @Operation(summary = "Get all users in the database with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            }),
            @ApiResponse(responseCode = "400", description = "Validation failed of the request parameters data"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    @Override
    public ResponseEntity<Object> getAllByPage(
            @Valid
            @Min(value = 1, message = "The page number must be greater than 0, not equal 0.")
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @Valid
            @Min(value = 1, message = "The size must be greater than or equal to 1.")
            @RequestParam(defaultValue = "1") Integer size
    ) {
        return ResponseEntity.ok(userRepository.getPageListUser(Pageable.ofSize(size).withPage(page)));
    }

    @GetMapping("/role")
    @Operation(summary = "Get all users by role name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            }),
            @ApiResponse(responseCode = "404", description = "The role name wasn't found in the database"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    public ResponseEntity<Object> getAllByUserRole(@RequestParam String roleName) {
        return ResponseEntity.ok(userRepository.findByRole_RoleName(roleName));
    }

    @GetMapping("/role/page")
    @Operation(summary = "Get all users by role name with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            }),
            @ApiResponse(responseCode = "400", description = "Validation failed of the request parameters data"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    public ResponseEntity<Object> getPageByUserRole(@RequestParam String roleName,
                                                    @Valid
                                                    @Min(value = 1, message = "The page number must be greater than 0, not equal 0.")
                                                    @RequestParam(required = false, defaultValue = "1") Integer page,
                                                    @Valid
                                                    @Min(value = 1, message = "The size must be greater than or equal to 1.")
                                                    @RequestParam(defaultValue = "1") Integer size
    ) {
        return ResponseEntity.ok(userRepository.findByRole_RoleName(roleName, Pageable.ofSize(size).withPage(page)));
    }

    @PostMapping("/grant_role")
    @Operation(summary = "Grant a role to a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role granted successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            }),
            @ApiResponse(responseCode = "404", description = "The userId or role name wasn't found in the database"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    public ResponseEntity<Object> grantUserRole(
            @RequestParam String userId,
            @RequestParam String roleName) {
        UserRole role = userRoleRepository.findByRoleName(roleName).orElseThrow(() -> new EntityNotFoundException("The role '" + roleName + "' not found!"));
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new EntityNotFoundException("The userId '" + userId + "' wasn't found!"));
        user.setRole(role);
        return ResponseEntity.ok(userRepository.save(user));
    }
}
