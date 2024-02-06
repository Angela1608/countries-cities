package com.countries.cities.service;

import com.countries.cities.model.Role;
import java.util.Set;

public interface RoleService {

    Set<Role> findAllByRoleNames(Set<Role.RoleName> roleNames);

}
