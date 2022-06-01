package org.example.bootstrap5rest.service;

import org.example.bootstrap5rest.model.Role;

import java.util.List;

public interface RoleService {

    Role findByRoleName(String roleName);

    List<Role> findAll();

    void save(Role role);
}
