package com.countries.cities.util;

import com.countries.cities.dto.request.CityRequestDto;
import com.countries.cities.dto.request.CitySearchParameters;
import com.countries.cities.dto.request.CountryRequestDto;
import com.countries.cities.dto.response.CityDto;
import com.countries.cities.dto.response.CountryDto;
import com.countries.cities.model.City;
import com.countries.cities.model.Country;
import java.util.HashSet;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PredefinedEntities {

    public static final City CITY = City.builder()
            .id(1L)
            .name("Madrid")
            .logoLink("logoLink")
            .country(new Country())
            .build();

    public static final CitySearchParameters CITY_SEARCH_PARAMETERS = CitySearchParameters.builder()
            .cityNames(new String[]{"City1", "City2", "City3"})
            .countryNames(new String[]{"Country1", "Country2"})
            .build();

    public static final CityDto CITY_DTO = CityDto.builder()
            .id(1L)
            .name("Madrid")
            .logoLink("logoLink")
            .countryDto(new CountryDto(1L, "Spain"))
            .build();

    public static final CityRequestDto CITY_REQUEST_DTO = CityRequestDto.builder()
            .name("Madrid")
            .logoLink("logoLink")
            .countryId(1L)
            .build();

    public static final Country COUNTRY = Country.builder()
            .id(1L)
            .name("Spain")
            .cities(new HashSet<>())
            .build();

    public static final CountryRequestDto COUNTRY_REQUEST_DTO = CountryRequestDto.builder()
            .name("Spain")
            .build();

    public static final CountryDto COUNTRY_DTO = CountryDto.builder()
            .id(1L)
            .name("Spain")
            .build();

}
