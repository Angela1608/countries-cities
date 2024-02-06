package com.countries.cities.service;

import com.countries.cities.dto.request.CityRequestDto;
import com.countries.cities.dto.request.CitySearchParameters;
import com.countries.cities.dto.response.CityDto;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface CityService {

    Page<CityDto> search(CitySearchParameters searchParameters, Pageable pageable);

    CityDto createCity(CityRequestDto cityRequestDto, MultipartFile logo);

    CityDto updateCity(CityRequestDto requestDto, Long id, MultipartFile logo);

    Set<String> getAllUniqueCitiesNames();

}
