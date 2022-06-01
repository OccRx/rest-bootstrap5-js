package org.example.bootstrap5.service;

import org.example.bootstrap5.repositories.UserRepository;
import org.example.bootstrap5.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bcrypt;

    @Autowired
    public UserServiceImp(UserRepository userRepository, BCryptPasswordEncoder bcrypt) {
        this.userRepository = userRepository;
        this.bcrypt = bcrypt;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void save(User user) {
        user.setPassword(bcrypt.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
       return userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void updateUser(User user) {
        String oldPassword = userRepository.findUserById(user.getId()).getPassword();
        if(oldPassword.equals(user.getPassword())){
            userRepository.save(user);
        } else {
            user.setPassword(bcrypt.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

}
