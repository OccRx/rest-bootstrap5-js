package org.my.bootstrap5.controller;

import org.my.bootstrap5.model.Role;
import org.my.bootstrap5.model.User;
import org.my.bootstrap5.service.RoleService;
import org.my.bootstrap5.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String starterPage(@AuthenticationPrincipal UserDetails userDetails, Model model){
        User user = userService.findUserByEmail(userDetails.getUsername());
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("roleList", roleService.findAll());
        model.addAttribute("user", user);
        return "admin";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:";
    }

    @PostMapping(value = "/update")
    public String updateUser(@ModelAttribute("user") User user) {
        if(user.getRoles()== null){
            user.setRoles(List.of(roleService.findByRoleName("ROLE_USER")));
        }
        userService.updateUser(user);
        return "redirect:";
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam(value = "id") Long id) {
        userService.deleteUserById(id);
        return "redirect:";
    }
}
