package com.countries.cities.dto.response;

import lombok.Builder;

@Builder
public record CityDto(Long id,
                      String name,
                      String logoLink,
                      CountryDto countryDto) {

}
