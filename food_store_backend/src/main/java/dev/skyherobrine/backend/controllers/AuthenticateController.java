package dev.skyherobrine.backend.controllers;

import dev.skyherobrine.backend.dtos.UserDto;
import dev.skyherobrine.backend.models.oracle.User;
import dev.skyherobrine.backend.projects.UserProject;
import dev.skyherobrine.backend.repositories.oracle.UserRepository;
import dev.skyherobrine.backend.services.AuthenticateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authenticate")
@Tag(name = "Authenticate Controller", description = "Using for authenticating like login, register, update,... information account for users")
@RequiredArgsConstructor
public class AuthenticateController {

    private final UserRepository userRepository;
    private final AuthenticateService authenticateService;

    @PostMapping("/login")
    @Operation(summary = "Use for logging in account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successfully, account found and get information", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserProject.class))
            }),
            @ApiResponse(responseCode = "404", description = "The email and password for this account wasn't existed"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    public ResponseEntity<Object> checkLogin(
            @RequestParam String email,
            @RequestParam String password
    ) {
        return ResponseEntity.ok(authenticateService.checkLogin(email, password));
    }

    @PostMapping("/register")
    @Operation(summary = "Register account for the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Register account successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserProject.class))
            }),
            @ApiResponse(responseCode = "400", description = "Validation failed of the form data"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    public ResponseEntity<Object> registerAccount(
            @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Provide the information for registering account",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            ) @RequestBody UserDto dto
    ) {
        return ResponseEntity.ok(authenticateService.registerAccount(dto));
    }

    @PutMapping("/update/{userId}")
    @Operation(summary = "Updating the information of the account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updating account successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserProject.class))
            }),
            @ApiResponse(responseCode = "400", description = "Validation failed of the form dat"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    public ResponseEntity<Object> updateAccount(
            @PathVariable("userId") String userId,
            @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Provide the information for changing",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            )
            @RequestBody UserDto dto) {
        return ResponseEntity.ok(authenticateService.updateAccount(userId, dto));
    }
}
