package com.countries.cities.repository.city.spec;

import com.countries.cities.model.City;
import com.countries.cities.repository.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CityNameSpecificationProvider implements SpecificationProvider<City> {

    @Override
    public String getKey() {
        return "cityName";
    }

    public Specification<City> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) ->
                root.get("name").in(Arrays.stream(params).toArray());
    }

}
