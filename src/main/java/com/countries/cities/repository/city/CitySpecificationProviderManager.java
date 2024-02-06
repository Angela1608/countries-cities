package com.countries.cities.repository.city;

import com.countries.cities.model.City;
import com.countries.cities.repository.SpecificationProvider;
import com.countries.cities.repository.SpecificationProviderManager;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CitySpecificationProviderManager implements SpecificationProviderManager<City> {

    private final Set<SpecificationProvider<City>> citySpecificationProviders;

    @Override
    public SpecificationProvider<City> getSpecificationProvider(String key) {
        return citySpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Can't find correct specification provider for key " + key));
    }

}
