package com.countries.cities.service;

import com.countries.cities.dto.request.UserRegistrationRequestDto;
import com.countries.cities.dto.response.UserRegistrationResponseDto;

public interface UserService {

    UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto);

}
