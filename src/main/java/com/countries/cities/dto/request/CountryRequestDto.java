package com.countries.cities.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryRequestDto {

    @NotBlank(message = "Country name is required")
    private String name;

    @JsonCreator
    public CountryRequestDto(@JsonProperty("name") String name) {
        this.name = name;
    }

}
