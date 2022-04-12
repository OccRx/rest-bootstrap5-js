package my.springboot_mvcjpa_231.service;


import my.springboot_mvcjpa_231.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAll();

    User findUserById(long id);

    void add(User user);

    void update(User newUser);

    void delete(long id);

    User findUserByName(String name);
}
