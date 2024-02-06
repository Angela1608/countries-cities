package com.countries.cities.service.impl;

import com.countries.cities.dto.request.CountryRequestDto;
import com.countries.cities.dto.response.CountryDto;
import com.countries.cities.mapper.CountryMapper;
import com.countries.cities.model.Country;
import com.countries.cities.repository.country.CountryRepository;
import com.countries.cities.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public CountryDto createCountry(CountryRequestDto countryRequestDto) {
        Country savedCountry = countryRepository.save(countryMapper.toEntity(countryRequestDto));
        return countryMapper.toDto(savedCountry);
    }

}
