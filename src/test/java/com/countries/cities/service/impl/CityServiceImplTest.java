package com.countries.cities.service.impl;

import com.countries.cities.mapper.CityMapper;
import com.countries.cities.model.City;
import com.countries.cities.repository.city.CityRepository;
import com.countries.cities.repository.city.CitySpecificationBuilder;
import com.countries.cities.repository.country.CountryRepository;
import com.countries.cities.s3.StorageClient;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

import static com.countries.cities.util.PredefinedEntities.CITY;
import static com.countries.cities.util.PredefinedEntities.CITY_DTO;
import static com.countries.cities.util.PredefinedEntities.CITY_REQUEST_DTO;
import static com.countries.cities.util.PredefinedEntities.CITY_SEARCH_PARAMETERS;
import static com.countries.cities.util.PredefinedEntities.COUNTRY;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CitySpecificationBuilder citySpecificationBuilder;

    @Mock
    private CityMapper cityMapper;

    @Mock
    private StorageClient storageClient;

    @Mock
    private Specification<City> specification;

    @InjectMocks
    private CityServiceImpl cityService;

    @Test
    void whenSearchCalled_thenCitiesAreReturned() {
        Pageable pageable = PageRequest.of(0, 10);
        List<City> cities = Collections.singletonList(CITY);

        when(citySpecificationBuilder.build(CITY_SEARCH_PARAMETERS)).thenReturn(specification);
        when(cityRepository.findAll(specification, pageable)).thenReturn(new PageImpl<>(cities));
        when(cityMapper.toDto(any())).thenReturn(CITY_DTO);

        cityService.search(CITY_SEARCH_PARAMETERS, pageable);

        verify(citySpecificationBuilder).build(CITY_SEARCH_PARAMETERS);
        verify(cityRepository).findAll(specification, pageable);
        verify(cityMapper).toDto(any());
    }

    @Test
    void whenCreateCityCalled_thenCityIsCreatedSuccessfully() {
        when(countryRepository.findById(any())).thenReturn(java.util.Optional.of(COUNTRY));
        when(cityMapper.toEntity(any(), any())).thenReturn(CITY);
        when(cityRepository.save(any())).thenReturn(CITY);
        when(cityMapper.toDto(any())).thenReturn(CITY_DTO);

        MultipartFile logo = new MockMultipartFile("logo", new byte[0]);

        cityService.createCity(CITY_REQUEST_DTO, logo);

        verify(countryRepository, times(1)).findById(any());
        verify(cityMapper, times(1)).toEntity(any(), any());
        verify(cityRepository, times(1)).save(any());
        verify(cityMapper, times(1)).toDto(any());
    }

    @Test
    void whenCreateCityCalledWithNonExistingCountry_thenEntityNotFoundExceptionIsThrown() {
        MultipartFile logo = new MockMultipartFile("logo", new byte[0]);

        when(countryRepository.findById(any())).thenReturn(java.util.Optional.empty());

        assertThrows(
            EntityNotFoundException.class, () -> cityService.createCity(CITY_REQUEST_DTO, logo));

        verify(countryRepository, times(1)).findById(any());
    }

    @Test
    void whenUpdateCityCalled_thenCityIsUpdatedSuccessfully() {
        when(cityRepository.findById(CITY.getId())).thenReturn(java.util.Optional.of(CITY));
        when(cityRepository.save(any())).thenReturn(CITY);
        when(cityMapper.toDto(any())).thenReturn(CITY_DTO);

        MultipartFile logo = new MockMultipartFile("logo", new byte[0]);

        cityService.updateCity(CITY_REQUEST_DTO, CITY.getId(), logo);

        verify(cityRepository, times(1)).findById(CITY.getId());
        verify(cityRepository, times(1)).save(CITY);
        verify(cityMapper, times(1)).toDto(CITY);
    }

    @Test
    void whenUpdateCityCalledWithNonExistingCity_thenEntityNotFoundExceptionIsThrown() {
        Long cityId = 2L;
        MultipartFile logo = new MockMultipartFile("logo", new byte[0]);

        when(cityRepository.findById(cityId)).thenReturn(java.util.Optional.empty());

        assertThrows(
            EntityNotFoundException.class,
            () -> cityService.updateCity(CITY_REQUEST_DTO, cityId, logo));

        verify(cityRepository, times(1)).findById(cityId);
    }

    @Test
    void whenGetAllUniqueCitiesNamesCalled_thenCitiesNamesAreReturned() {
        when(cityRepository.findAll()).thenReturn(Collections.singletonList(CITY));

        cityService.getAllUniqueCitiesNames();

        verify(cityRepository, times(1)).findAll();
    }

    @Test
    void whenGetAllUniqueCitiesNamesCalledWithEmptyList_thenEmptyListIsReturned() {
        when(cityRepository.findAll()).thenReturn(List.of());

        cityService.getAllUniqueCitiesNames();

        verify(cityRepository, times(1)).findAll();
    }

}
