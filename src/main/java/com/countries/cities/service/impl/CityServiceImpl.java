package com.countries.cities.service.impl;

import com.countries.cities.dto.request.CityRequestDto;
import com.countries.cities.dto.request.CitySearchParameters;
import com.countries.cities.dto.response.CityDto;
import com.countries.cities.mapper.CityMapper;
import com.countries.cities.model.City;
import com.countries.cities.model.Country;
import com.countries.cities.repository.city.CityRepository;
import com.countries.cities.repository.city.CitySpecificationBuilder;
import com.countries.cities.repository.country.CountryRepository;
import com.countries.cities.s3.StorageClient;
import com.countries.cities.service.CityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final CitySpecificationBuilder citySpecificationBuilder;
    private final CityMapper cityMapper;
    private final StorageClient storageClient;

    @Override
    public Page<CityDto> search(CitySearchParameters params, Pageable pageable) {
        Specification<City> citySpecification = citySpecificationBuilder.build(params);
        List<CityDto> cityDtos = cityRepository
            .findAll(citySpecification, pageable)
            .stream()
            .map(cityMapper::toDto)
            .toList();
        return new PageImpl<>(cityDtos, pageable, cityDtos.size());
    }

    @Override
    @Transactional
    public CityDto createCity(CityRequestDto cityRequestDto, MultipartFile logo) {
        Country country = countryRepository.findById(cityRequestDto.getCountryId())
            .orElseThrow(() -> new EntityNotFoundException(
                String.format("Can't find country by id '%s'",
                    cityRequestDto.getCountryId())));
        String logoLink = uploadLogo(logo);
        cityRequestDto.setLogoLink(logoLink);
        City savedCity = cityRepository.save(cityMapper.toEntity(cityRequestDto, country));
        return cityMapper.toDto(savedCity);
    }

    @Override
    @Transactional
    public CityDto updateCity(CityRequestDto requestDto, Long id, MultipartFile logo) {
        City existingCity = cityRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format("Can't find city by id '%s'", id)));
        existingCity.setName(requestDto.getName());

        String newLogoLink = uploadLogo(logo);
        existingCity.setLogoLink(newLogoLink);

        City updatedCity = cityRepository.save(existingCity);
        return cityMapper.toDto(updatedCity);
    }

    @Override
    public Set<String> getAllUniqueCitiesNames() {
        return cityRepository.findAll().stream()
            .map(City::getName)
            .collect(Collectors.toSet());
    }

    @SneakyThrows
    private String uploadLogo(MultipartFile logo) {
        return storageClient.putContent(logo.getOriginalFilename(), logo.getBytes());
    }

}
