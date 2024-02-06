package com.countries.cities.service.impl;

import com.countries.cities.model.Role;
import com.countries.cities.repository.user.RoleRepository;
import com.countries.cities.service.RoleService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Set<Role> findAllByRoleNames(Set<Role.RoleName> roleNames) {
        return roleRepository.findAllByRoleNameIn(roleNames);
    }

}
