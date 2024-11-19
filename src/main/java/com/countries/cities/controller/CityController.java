package com.countries.cities.controller;

import com.countries.cities.controller.swagger.CitySwaggerApi;
import com.countries.cities.dto.request.CityRequestDto;
import com.countries.cities.dto.request.CitySearchParameters;
import com.countries.cities.dto.response.CityDto;
import com.countries.cities.service.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RestController
@RequestMapping("api/v1/cities")
@RequiredArgsConstructor
@Validated
public class CityController implements CitySwaggerApi {

    private final CityService cityService;
    private final ObjectMapper objectMapper;

    @GetMapping("/search")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Page<CityDto>> searchCities(
        CitySearchParameters params,
        Pageable pageable) {
        return ResponseEntity.ok(cityService.search(params, pageable));
    }

    @GetMapping("/unique")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Set<String>> getUniqueCitiesNames() {
        return ResponseEntity.ok(cityService.getAllUniqueCitiesNames());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    @SneakyThrows
    public ResponseEntity<CityDto> updateCity(
        @Valid @RequestParam("requestDto") String requestDto,
        @PathVariable Long id,
        @RequestParam("logo") MultipartFile logo) {
        CityRequestDto cityRequestDto = objectMapper.readValue(requestDto, CityRequestDto.class);
        return ResponseEntity.ok(cityService.updateCity(cityRequestDto, id, logo));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    @SneakyThrows
    public ResponseEntity<CityDto> createCity(
        @Valid @RequestParam("requestDto") String requestDto,
        @RequestParam("logo") MultipartFile logo) {
        CityRequestDto cityRequestDto = objectMapper.readValue(requestDto, CityRequestDto.class);
        return ResponseEntity.ok(cityService.createCity(cityRequestDto, logo));
    }

}
