package com.countries.cities.controller;

import com.countries.cities.controller.swagger.CountrySwaggerApi;
import com.countries.cities.dto.request.CountryRequestDto;
import com.countries.cities.dto.response.CountryDto;
import com.countries.cities.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/countries")
@RequiredArgsConstructor
@Validated
public class CountryController implements CountrySwaggerApi {

    private final CountryService countryService;

    @PostMapping
    @PreAuthorize(value = "hasRole('ROLE_EDITOR')")
    public CountryDto createCountry(
            @Valid @RequestBody CountryRequestDto requestDto) {
        return countryService.createCountry(requestDto);
    }

}
