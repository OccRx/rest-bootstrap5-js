package org.example.bootstrap5rest.controller;

import org.example.bootstrap5rest.model.Role;
import org.example.bootstrap5rest.model.User;
import org.example.bootstrap5rest.service.RoleService;
import org.example.bootstrap5rest.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    private final RoleService roleService;

    public UserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user){
        userService.save(user);
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @GetMapping("/authUser")
    public User getAuthUser(@AuthenticationPrincipal UserDetails userDetails){
        return userService.findUserByEmail(userDetails.getUsername());
    }
}
