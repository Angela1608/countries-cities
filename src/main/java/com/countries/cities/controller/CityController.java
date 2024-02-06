package com.countries.cities.controller;

import com.countries.cities.controller.swagger.CitySwaggerApi;
import com.countries.cities.dto.request.CityRequestDto;
import com.countries.cities.dto.request.CitySearchParameters;
import com.countries.cities.dto.response.CityDto;
import com.countries.cities.service.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

@RestController
@RequestMapping("api/v1/cities")
@RequiredArgsConstructor
@Validated
public class CityController implements CitySwaggerApi {

    private final CityService cityService;
    private final ObjectMapper objectMapper;

    @GetMapping("/search")
    @PreAuthorize(value = "hasRole('ROLE_USER')")
    public Page<CityDto> searchCities(CitySearchParameters params, Pageable pageable) {
        return cityService.search(params, pageable);
    }

    @GetMapping("/unique")
    @PreAuthorize(value = "hasRole('ROLE_USER')")
    public Set<String> getUniqueCitiesNames() {
        return cityService.getAllUniqueCitiesNames();
    }

    @PutMapping("/{id}")
    @PreAuthorize(value = "hasRole('ROLE_EDITOR')")
    @SneakyThrows
    public CityDto updateCity(@Valid @RequestParam("requestDto") String requestDto,
                              @PathVariable Long id, @RequestParam("logo") MultipartFile logo) {
        CityRequestDto cityRequestDto = objectMapper.readValue(requestDto, CityRequestDto.class);
        return cityService.updateCity(cityRequestDto, id, logo);
    }

    @PostMapping
    @PreAuthorize(value = "hasRole('ROLE_EDITOR')")
    @SneakyThrows
    public CityDto createCity(@Valid @RequestParam("requestDto") String requestDto,
                              @RequestParam("logo") MultipartFile logo) {
        CityRequestDto cityRequestDto = objectMapper.readValue(requestDto, CityRequestDto.class);
        return cityService.createCity(cityRequestDto, logo);
    }

}
