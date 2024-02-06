package com.countries.cities.dto.request;

import lombok.Builder;

@Builder
public record CitySearchParameters(String[] cityNames, String[] countryNames) {

}
