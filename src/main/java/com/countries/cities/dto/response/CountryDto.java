package com.countries.cities.dto.response;

import lombok.Builder;

@Builder
public record CountryDto(Long id, String name) {

}
