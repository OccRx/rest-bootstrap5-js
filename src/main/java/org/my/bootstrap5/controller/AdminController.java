package org.my.bootstrap5.controller;

import org.my.bootstrap5.model.Role;
import org.my.bootstrap5.repositories.RoleRepository;
import org.my.bootstrap5.model.User;
import org.my.bootstrap5.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("")
    public String starterPage(@AuthenticationPrincipal UserDetails userDetails, Model model){
        User user = userService.findUserByEmail(userDetails.getUsername());
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("roleList", roleRepository.findAll());
        model.addAttribute("user", user);
        return "adminPanel";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:";
    }

    @PostMapping(value = "/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:";
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam(value = "id") Long id) {
        userService.deleteUserById(id);
        return "redirect:";
    }
}
