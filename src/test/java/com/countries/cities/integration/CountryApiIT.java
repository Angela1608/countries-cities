package com.countries.cities.integration;

import com.countries.cities.annotation.IT;
import com.countries.cities.service.CountryService;
import com.countries.cities.util.TestUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IT
public class CountryApiIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CountryService countryService;

    private static final String BASE_URL = "/api/v1/countries";

    @Test
    @SneakyThrows
    @WithMockUser(
        username = "editor@example.com",
        roles = {"EDITOR"})
    void whenCreatingCountry_thenReturnsCreatedCountry() {
        String requestDto =
            TestUtils.getJsonStringFromFile(
                "/testdata/countryRequestDto.json", new TypeReference<>() {
                }, objectMapper);

        mockMvc
            .perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(requestDto))
            .andExpect(status().isOk());
    }
}
