//package org.my.bootstrap5.controller;
//
//import org.my.bootstrap5.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/user")
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService usersService) {
//        this.userService = usersService;
//    }
//
//    @GetMapping("")
//    public String printUser(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//        model.addAttribute("user", userService.findUserByName(userDetails.getUsername()));
//        return "user";
//    }
//
//}
