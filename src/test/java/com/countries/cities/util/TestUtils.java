package com.countries.cities.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;

public class TestUtils {

  public static String getJsonStringFromFile(
      String filePath, TypeReference<?> typeReference, ObjectMapper objectMapper)
      throws IOException {
    Object object =
        objectMapper.readValue(
            new ClassPathResource(filePath).getContentAsByteArray(), typeReference);
    return objectMapper.writeValueAsString(object);
  }

}
