package com.countries.cities.repository.city;

import com.countries.cities.dto.request.CitySearchParameters;
import com.countries.cities.model.City;
import com.countries.cities.repository.SpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CitySpecificationBuilder implements SpecificationBuilder<City> {

    private final CitySpecificationProviderManager providerManager;

    @Override
    public Specification<City> build(CitySearchParameters searchParametersDto) {
        Specification<City> spec = Specification.where(null);
        if (searchParametersDto.cityNames() != null && searchParametersDto.cityNames().length > 0) {
            spec = spec.and(providerManager
                    .getSpecificationProvider("cityName")
                    .getSpecification(searchParametersDto.cityNames()));
        }
        if (searchParametersDto.countryNames() != null
                && searchParametersDto.countryNames().length > 0) {
            spec = spec.and(providerManager
                    .getSpecificationProvider("countryName")
                    .getSpecification(searchParametersDto.countryNames()));
        }
        return spec;
    }

}
