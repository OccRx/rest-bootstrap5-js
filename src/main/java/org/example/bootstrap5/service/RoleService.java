package org.example.bootstrap5.service;

import org.example.bootstrap5.model.Role;

import java.util.List;

public interface RoleService {

    Role findByRoleName(String roleName);

    List<Role> findAll();

    void save(Role role);
}
