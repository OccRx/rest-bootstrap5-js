package my.springboot_mvcjpa_231.service;


import my.springboot_mvcjpa_231.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User getUserById(long id);

    void add(User user);

    void update(User newUser);

    void delete(long id);
}
