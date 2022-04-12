package my.springboot_mvcjpa_231.DAO;

import my.springboot_mvcjpa_231.model.User;

import java.util.List;

public interface UserDAO {

    List<User> findAll();

    User findUserById(long id);

    void add(User user);

    void update(User newUser);

    void delete(long id);

    User findUserByName(String name);
}
