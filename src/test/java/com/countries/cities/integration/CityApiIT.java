package com.countries.cities.integration;

import com.countries.cities.annotation.IT;
import com.countries.cities.service.CityService;
import com.countries.cities.util.TestUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IT
class CityApiIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CityService cityService;

    private static final String BASE_URL = "/api/v1/cities";
    private static final String CITY_NAMES_QUERY = "cityNames=Kyiv";

    @Test
    @SneakyThrows
    @WithMockUser(
        username = "user@example.com")
    void whenSearchingCities_thenReturnsOkStatus() {
        mockMvc.perform(get(BASE_URL + "/search?" + CITY_NAMES_QUERY)).andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @WithMockUser(
        username = "user@example.com")
    void whenGettingUniqueCitiesNames_thenReturnsOkStatusAndJsonContentType() {
        mockMvc
            .perform(get(BASE_URL + "/unique"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @SneakyThrows
    @WithMockUser(
        username = "editor@example.com",
        roles = {"EDITOR"})
    void whenEditingCity_thenReturnsOkStatus() {
        Long cityId = 1L;
        String requestDto =
            TestUtils.getJsonStringFromFile(
                "/testdata/cityRequestDto.json", new TypeReference<>() {
                }, objectMapper);

        MockMultipartFile logoFile =
            new MockMultipartFile("logo", "logo.jpg", "image/jpeg", "logo-content".getBytes());

        mockMvc
            .perform(
                multipart(BASE_URL + "/{id}", cityId)
                    .file(logoFile)
                    .param("requestDto", requestDto)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .with(
                        request -> {
                            request.setMethod("PUT");
                            return request;
                        }))
            .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @WithMockUser(
        username = "editor@example.com",
        roles = {"EDITOR"})
    void whenCreatingCity_thenReturnsOkStatus() {
        String requestDto =
            TestUtils.getJsonStringFromFile(
                "/testdata/cityRequestDto.json", new TypeReference<>() {
                }, objectMapper);

        MockMultipartFile logoFile =
            new MockMultipartFile("logo", "logo.jpg", "image/jpeg", "logo-content".getBytes());

        mockMvc
            .perform(
                multipart(BASE_URL)
                    .file(logoFile)
                    .param("requestDto", requestDto)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .with(
                        request -> {
                            request.setMethod("POST");
                            return request;
                        }))
            .andExpect(status().isOk());
    }
}
