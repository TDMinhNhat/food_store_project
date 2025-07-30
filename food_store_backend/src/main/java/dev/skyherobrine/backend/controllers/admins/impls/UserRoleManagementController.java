package dev.skyherobrine.backend.controllers.admins.impls;

import dev.skyherobrine.backend.controllers.admins.IManagement;
import dev.skyherobrine.backend.dtos.UserRoleDto;
import dev.skyherobrine.backend.models.oracle.UserRole;
import dev.skyherobrine.backend.repositories.oracle.UserRoleRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admins/user_role")
@Tag(name = "User Role Management (Admin)", description = "Manage user roles in the system, including adding, updating, deleting, and retrieving user roles.")
@RequiredArgsConstructor
public class UserRoleManagementController implements IManagement<UserRoleDto, Long> {

    private final UserRoleRepository userRoleRepository;

    @PostMapping
    @Operation(summary = "Add a new user role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Add user role into database successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserRole.class))
            }),
            @ApiResponse(responseCode = "400", description = "Validation failed of the form data"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    @Override
    public ResponseEntity<Object> add(@Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The user role information to add",
            required = true,
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserRoleDto.class))
    ) @RequestBody UserRoleDto dto) {
        return ResponseEntity.ok(userRoleRepository.save(dto.toObject()));
    }

    @Override
    public ResponseEntity<Object> addMany(List<UserRoleDto> list) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(Long aLong, UserRoleDto userRoleDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(Long aLong) {
        return null;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user role information by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get the user role information successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserRole.class))
            }),
            @ApiResponse(responseCode = "404", description = "The user role id wasn't found in database")
    })
    @Override
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userRoleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The user role id = " + id + " wasn't found!")));
    }

    @GetMapping
    @Operation(summary = "Get all user roles exist in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all user roles successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            })
    })
    @Override
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(userRoleRepository.findAll());
    }

    @Override
    public ResponseEntity<Object> getAllByPage(Integer page, Integer size) {
        return null;
    }
}
