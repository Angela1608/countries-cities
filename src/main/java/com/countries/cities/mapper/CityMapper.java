package com.countries.cities.mapper;

import com.countries.cities.dto.request.CityRequestDto;
import com.countries.cities.dto.response.CityDto;
import com.countries.cities.model.City;
import com.countries.cities.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Mapping(target = "countryDto.id", source = "city.country.id")
    @Mapping(target = "countryDto.name", source = "city.country.name")
    CityDto toDto(City city);

    @Mapping(target = "name", source = "cityRequestDto.name")
    @Mapping(target = "id", ignore = true)
    City toEntity(CityRequestDto cityRequestDto, Country country);

}
