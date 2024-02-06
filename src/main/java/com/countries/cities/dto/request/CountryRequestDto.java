package com.countries.cities.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryRequestDto {

    @NotBlank(message = "Country name is required")
    private String name;

}
