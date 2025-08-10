package com.example.kyc.controller;

import com.example.kyc.dto.UserProfileResponse;
import com.example.kyc.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@Validated
@Tag(name = "User Profile API", description = "Endpoints for managing user KYC profiles")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/by-email")
    @Operation(summary = "Get user profile by email",
            description = "Retrieves a user's KYC information using their email address")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User profile found"),
        @ApiResponse(responseCode = "400", description = "Invalid email format"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserProfileResponse> getUserByEmail(
            @Parameter(description = "Email address of the user", required = true)
            @RequestParam @NotBlank @Email String email) {
        return ResponseEntity.ok(userProfileService.findByEmail(email));
    }
}
