package com.countries.cities.controller.swagger;

import com.countries.cities.dto.request.CountryRequestDto;
import com.countries.cities.dto.response.CountryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Country Management", description = "Endpoints for managing countries")
public interface CountrySwaggerApi {

    @Operation(summary = "Create a country",
        description = "Creates a new country")
    ResponseEntity<CountryDto> createCountry(
        @Valid @RequestBody CountryRequestDto requestDto);

}
