package com.countries.cities.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityRequestDto {

    @NotBlank(message = "City name is required")
    private String name;

    @JsonIgnore
    private String logoLink;

    private Long countryId;

}
