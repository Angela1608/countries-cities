package com.countries.cities.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.countries.cities.service.CityService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "jwt.expiration=30000000",
        "jwt.secret=c8YBk47Hy24fVr36DZehpWkgjAZi9cMl5dJq0LdF2wIg6SjPnQrTqWzZ3v2x5A8C",
        "aws.s3.region=us-east-1",
        "aws.s3.bucket=cities-and-countries",
        "aws.s3.credentials.access-key=AKIA5FTY7JEXBN4JKHQT",
        "aws.s3.credentials.secret-key=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",
        "aws.s3.host=http://localhost:9000"
})
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    @Test
    @SneakyThrows
    @WithMockUser(username = "doedoe@example.com", roles = {"USER"})
    void searchCities_ReturnsOkStatus() {
        mockMvc.perform(get("/api/v1/cities/search?cityNames=Kyiv"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "doedoe@example.com", roles = {"USER"})
    void getUniqueCitiesNames_ReturnsOkStatusAndJsonContentType() {
        mockMvc.perform(get("/api/v1/cities/unique"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "doedoe@example.com", roles = {"EDITOR"})
    void editCity_ReturnsOkStatus() {
        Long cityId = 1L; // Replace with your test data
        String requestDto = "{\"name\": \"Ukraine\", \"countryId\":2}";

        MockMultipartFile logoFile =
                new MockMultipartFile("logo", "logo.jpg", "image/jpeg", "logo-content".getBytes());

        mockMvc.perform(
                        MockMvcRequestBuilders.multipart("/api/v1/cities/{id}", cityId)
                                .file(logoFile)
                                .param("requestDto", requestDto)
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                                .with(request -> {
                                    request.setMethod("PUT");
                                    return request;
                                }))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "doedoe@example.com", roles = {"EDITOR"})
    void createCity_ReturnsOkStatus() {
        String requestDto = "{\"name\": \"Ukraine\", \"countryId\":2}";

        MockMultipartFile logoFile =
                new MockMultipartFile("logo", "logo.jpg", "image/jpeg", "logo-content".getBytes());

        mockMvc.perform(
                        MockMvcRequestBuilders.multipart("/api/v1/cities")
                                .file(logoFile)
                                .param("requestDto", requestDto)
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                                .with(request -> {
                                    request.setMethod("POST");
                                    return request;
                                }))
                .andExpect(status().isOk());
    }

}
