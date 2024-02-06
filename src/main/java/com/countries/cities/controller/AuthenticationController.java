package com.countries.cities.controller;

import com.countries.cities.controller.swagger.AuthenticationSwaggerApi;
import com.countries.cities.dto.request.UserLoginRequestDto;
import com.countries.cities.dto.request.UserRegistrationRequestDto;
import com.countries.cities.dto.response.UserLoginResponseDto;
import com.countries.cities.dto.response.UserRegistrationResponseDto;
import com.countries.cities.exception.RegistrationException;
import com.countries.cities.security.AuthenticationService;
import com.countries.cities.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationSwaggerApi {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public UserRegistrationResponseDto register(
            @Valid @RequestBody UserRegistrationRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@Valid @RequestBody UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }

}
