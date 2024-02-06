package com.countries.cities.mapper;

import com.countries.cities.dto.request.CityRequestDto;
import com.countries.cities.dto.response.CityDto;
import com.countries.cities.model.City;
import com.countries.cities.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

@Mapper(imports = MapperConfig.class, componentModel = "spring")
public interface CityMapper {

    @Mapping(target = "id", source = "city.id")
    @Mapping(target = "name", source = "city.name")
    @Mapping(target = "logoLink", source = "city.logoLink")
    @Mapping(target = "countryDto.id", source = "city.country.id")
    @Mapping(target = "countryDto.name", source = "city.country.name")
    CityDto toDto(City city);

    @Mapping(target = "name", source = "cityRequestDto.name")
    @Mapping(target = "country", source = "country")
    @Mapping(target = "id", ignore = true)
    City toEntity(CityRequestDto cityRequestDto, Country country);

}
