package my.springboot_mvcjpa_231.controller;

import my.springboot_mvcjpa_231.DAO.RoleRepo;
import my.springboot_mvcjpa_231.model.User;
import my.springboot_mvcjpa_231.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    private final UserService userService;
    private final RoleRepo roleRepo;

    public AdminUserController(UserService userService, RoleRepo roleRepo) {
        this.userService = userService;
        this.roleRepo = roleRepo;
    }

    @GetMapping("")
    public ModelAndView printUser(ModelAndView modelAndView) {
        modelAndView.addObject("userList", userService.findAll());
        modelAndView.setViewName("/allUser");
        return modelAndView;
    }

    @GetMapping(value = "/newUserForm")
    public String addUserForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roleList", roleRepo.findAll());
        return "/newUser";
    }

    @GetMapping(value = "/updateUserForm")
    public String updateForm(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "/update";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:";
    }

    @PostMapping(value = "/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:";
    }

    @GetMapping(value = "/delete")
    public String delete(@RequestParam(value = "id") Long id) {
        userService.delete(id);
        return "redirect:";
    }
}
