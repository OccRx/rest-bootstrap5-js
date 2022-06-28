package org.example.bootstrap5rest.service;

import org.example.bootstrap5rest.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<User> findAll();

    Optional<User> findUserById(long id);

    void save(User user);

    void updateUser(User newUser);

    void deleteUserById(long id);

    UserDetails loadUserByUsername(String email);
}
