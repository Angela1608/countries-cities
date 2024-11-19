package com.countries.cities.controller.swagger;

import com.countries.cities.dto.request.CitySearchParameters;
import com.countries.cities.dto.response.CityDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Tag(name = "City Management", description = "Endpoints for managing cities")
public interface CitySwaggerApi {

    @Operation(summary = "Get cities by parameters (paged)",
        description = "Returns cities by city names and countries names")
    ResponseEntity<Page<CityDto>> searchCities(
        @Parameter(description = "Search parameters for cities") CitySearchParameters params,
        @Parameter(description = "Pageable object for pagination") Pageable pageable);

    @Operation(summary = "Get list of unique cities' names",
        description = "Returns a list of unique cities names")
    ResponseEntity<Set<String>> getUniqueCitiesNames();

    @Operation(summary = "Update name and logo",
        description = "Updates city's name and logo by ID")
    ResponseEntity<CityDto> updateCity(
        @Parameter(
            description = "JSON string representing the city details. "
                + "Example: {'name': 'City Name', 'countryId': 1}")
        @Valid @RequestParam("requestDto") String requestDto,
        @Parameter(description = "ID of the city to update", in = ParameterIn.PATH)
        @PathVariable Long id,
        @Parameter(description = "New logo for the city")
        @RequestParam("logo") MultipartFile logo);

    @Operation(summary = "Create a city",
        description = "Creates a new city with provided details and logo")
    ResponseEntity<CityDto> createCity(
        @Parameter(
            description = "JSON string representing the city details. "
                + "Example: {'name': 'City Name', 'countryId': 1}")
        @Valid @RequestParam("requestDto") String requestDto,
        @Parameter(description = "City logo") @RequestParam("logo") MultipartFile logo);

}
