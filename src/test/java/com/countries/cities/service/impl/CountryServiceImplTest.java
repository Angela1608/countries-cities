package com.countries.cities.service.impl;

import com.countries.cities.mapper.CountryMapper;
import com.countries.cities.repository.country.CountryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.countries.cities.util.PredefinedEntities.COUNTRY;
import static com.countries.cities.util.PredefinedEntities.COUNTRY_DTO;
import static com.countries.cities.util.PredefinedEntities.COUNTRY_REQUEST_DTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryServiceImplTest {

    @InjectMocks
    private CountryServiceImpl countryService;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryMapper countryMapper;

    @Test
    void whenCreateCountryCalled_thenCountryIsCreatedSuccessfully() {
        when(countryMapper.toEntity(COUNTRY_REQUEST_DTO)).thenReturn(COUNTRY);
        when(countryRepository.save(any())).thenReturn(COUNTRY);
        when(countryMapper.toDto(COUNTRY)).thenReturn(COUNTRY_DTO);

        countryService.createCountry(COUNTRY_REQUEST_DTO);

        verify(countryMapper, times(1)).toEntity(COUNTRY_REQUEST_DTO);
        verify(countryRepository, times(1)).save(COUNTRY);
        verify(countryMapper, times(1)).toDto(COUNTRY);
    }

}
