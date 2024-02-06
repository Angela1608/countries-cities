package com.countries.cities.service;

import com.countries.cities.dto.request.CountryRequestDto;
import com.countries.cities.dto.response.CountryDto;

public interface CountryService {

    CountryDto createCountry(CountryRequestDto countryRequestDto);

}
