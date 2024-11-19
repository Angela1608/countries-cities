package com.countries.cities.mapper;

import com.countries.cities.dto.request.CountryRequestDto;
import com.countries.cities.dto.response.CountryDto;
import com.countries.cities.model.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDto toDto(Country country);

    Country toEntity(CountryRequestDto countryRequestDto);

}
