package com.countries.cities.controller.swagger;

import com.countries.cities.dto.request.UserLoginRequestDto;
import com.countries.cities.dto.request.UserRegistrationRequestDto;
import com.countries.cities.dto.response.UserLoginResponseDto;
import com.countries.cities.dto.response.UserRegistrationResponseDto;
import com.countries.cities.exception.RegistrationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Authentication management", description = "Endpoint for managing authentication")
public interface AuthenticationSwaggerApi {

    @Operation(summary = "Register user", description = "Registers a new user")
    UserRegistrationResponseDto register(
            @Valid @RequestBody UserRegistrationRequestDto request)
            throws RegistrationException;

    @Operation(summary = "Authenticate user", description = "Authenticates an existing user")
    UserLoginResponseDto login(@Valid @RequestBody UserLoginRequestDto request);

}
