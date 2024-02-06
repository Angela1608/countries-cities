package com.countries.cities.repository.city.spec;

import com.countries.cities.model.City;
import com.countries.cities.model.Country;
import com.countries.cities.repository.SpecificationProvider;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CountryNameSpecificationProvider implements SpecificationProvider<City> {

    @Override
    public String getKey() {
        return "countryName";
    }

    public Specification<City> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            Join<City, Country> countryJoin = root.join("country", JoinType.INNER);
            return countryJoin.get("name").in(Arrays.asList(params));
        };
    }

}

