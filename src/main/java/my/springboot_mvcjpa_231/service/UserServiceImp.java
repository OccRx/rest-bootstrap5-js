package my.springboot_mvcjpa_231.service;

import my.springboot_mvcjpa_231.DAO.UserDAO;
import my.springboot_mvcjpa_231.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "userService")
@Transactional
public class UserServiceImp implements UserService, UserDetailsService {

    private UserDAO userDAO;
    private BCryptPasswordEncoder bcrypt;

    @Autowired
    public UserServiceImp(UserDAO userDAO, BCryptPasswordEncoder bcrypt) {
        this.userDAO = userDAO;
        this.bcrypt = bcrypt;
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findUserById(long id) {
        return userDAO.findUserById(id);
    }

    @Override
    public void add(User user) {
        user.setPassword(bcrypt.encode(user.getPassword()));
        userDAO.add(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findUserByName(username);
        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),user.getRoles());
    }

    @Override
    public void update(User newUser) {
        userDAO.update(newUser);
    }

    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }

    @Override
    public User findUserByName(String name) {
        return userDAO.findUserByName(name);
    }

}
