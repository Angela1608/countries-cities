package com.countries.cities.service.impl;

import com.countries.cities.dto.request.UserRegistrationRequestDto;
import com.countries.cities.dto.response.UserRegistrationResponseDto;
import com.countries.cities.exception.RegistrationException;
import com.countries.cities.mapper.UserMapper;
import com.countries.cities.model.Role;
import com.countries.cities.model.User;
import com.countries.cities.repository.user.UserRepository;
import com.countries.cities.service.RoleService;
import com.countries.cities.service.UserService;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    @Override
    @Transactional
    public UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User is already registered");
        }
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        Set<Role> roles = roleService
                .findAllByRoleNames(requestDto.getRoles()
                        .stream()
                        .map(Role::getRoleName)
                        .collect(Collectors.toSet()));
        user.setRoles(roles);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

}
