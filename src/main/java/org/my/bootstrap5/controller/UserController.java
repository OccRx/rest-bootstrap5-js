package org.my.bootstrap5.controller;

import org.my.bootstrap5.model.User;
import org.my.bootstrap5.repositories.RoleRepository;
import org.my.bootstrap5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private RoleRepository roleRepository;

    @Autowired
    public UserController(UserService usersService, RoleRepository roleRepository) {
        this.userService = usersService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("")
    public String printUser(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//        User user = userService.findUserByEmail(userDetails.getUsername());
//        model.addAttribute("user", userService.findUserByName(userDetails.getUsername()));
        User user = userService.findUserByEmail(userDetails.getUsername());
        model.addAttribute("user", user);
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("roleList", roleRepository.findAll());
        return "adminPanel";
    }

}
