package com.countries.cities.repository;

import com.countries.cities.dto.request.CitySearchParameters;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {

    Specification<T> build(CitySearchParameters searchParametersDto);

}
