package org.example.bootstrap5rest.controller;

import org.example.bootstrap5rest.model.User;
import org.example.bootstrap5rest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(value = "/users/{id}")
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Optional<User> userNotNull = userService.findUserById(user.getId());
        userService.updateUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/authUser")
    public ResponseEntity<UserDetails> getAuthUser(@AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(userService.loadUserByUsername(userDetails.getUsername()), HttpStatus.OK);
    }

}
