package org.example.bootstrap5.controller;

import org.example.bootstrap5.service.UserService;
import org.example.bootstrap5.model.User;
import org.example.bootstrap5.service.RoleService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

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
        try{
            userService.save(user);
        } catch (RuntimeException e){
            return "error";
        }
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
