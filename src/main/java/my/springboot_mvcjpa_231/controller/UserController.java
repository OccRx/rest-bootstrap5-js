package my.springboot_mvcjpa_231.controller;

import my.springboot_mvcjpa_231.model.User;
import my.springboot_mvcjpa_231.service.UserService;
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

    private final UserService userService;

    @Autowired
    public UserController(UserService usersService) {
        this.userService = usersService;
    }

    @GetMapping("")
    public String printUser(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userService.findUserByName(userDetails.getUsername()));
        return "user";
    }

//    @GetMapping("")
//    public String printUser(
//            @AuthenticationPrincipal(expression = "@userServiceImp.findUserByName(username)") User user, Model model) {
//
//        model.addAttribute("user", user);
//        return "user";
//    }
}
