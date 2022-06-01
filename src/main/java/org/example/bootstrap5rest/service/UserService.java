package org.example.bootstrap5rest.service;

import org.example.bootstrap5rest.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAll();

    User findUserById(long id);

    void save(User user);

    void updateUser(User newUser);

    void deleteUserById(long id);

    User findUserByEmail(String email);
}
