package com.countries.cities.repository.user;

import com.countries.cities.model.Role;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findAllByRoleNameIn(Set<Role.RoleName> roleNames);

}
