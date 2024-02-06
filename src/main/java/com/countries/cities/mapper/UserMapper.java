package com.countries.cities.mapper;

import com.countries.cities.dto.response.UserRegistrationResponseDto;
import com.countries.cities.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;

@Mapper(imports = MapperConfig.class, componentModel = "spring")
public interface UserMapper {

    UserRegistrationResponseDto toDto(User user);

}
