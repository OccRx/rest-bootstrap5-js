package my.springboot_mvcjpa_231.controller;

import my.springboot_mvcjpa_231.model.User;
import my.springboot_mvcjpa_231.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/")
public class UserController {

    private UserService usersService;

    @Autowired
    public UserController(UserService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String userInfo(@ModelAttribute("user") User user){
        usersService.findAll();
        return "allUser";
    }
}
