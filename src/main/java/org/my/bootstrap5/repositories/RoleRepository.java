package org.my.bootstrap5.repositories;

import org.my.bootstrap5.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByRoleName(String roleName);

    List<Role> findAll();

}
