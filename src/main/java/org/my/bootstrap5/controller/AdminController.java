package org.my.bootstrap5.controller;

import org.my.bootstrap5.repositories.RoleRepository;
import org.my.bootstrap5.model.User;
import org.my.bootstrap5.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

//    @GetMapping("")
//    public ModelAndView printUser(ModelAndView modelAndView) {
//        modelAndView.addObject("userList", userService.findAll());
//        modelAndView.setViewName("/all");
//        return modelAndView;
//    }

    @GetMapping("/user")
    public String printUser(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userService.findUserByName(userDetails.getUsername()));
        return "user";
    }

    @GetMapping("/admin")
    public String starterPage(@AuthenticationPrincipal UserDetails userDetails, Model model){
        User user = userService.findUserByEmail(userDetails.getUsername());
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("roleList", roleRepository.findAll());
        model.addAttribute("user", user/*userService.findUserByEmail(userDetails.getUsername())*/);
//        model.addAttribute("userRole", user.getRoles());
        return "adminPanel";
    }

    @GetMapping(value = "/admin/newUserForm")
    public String addUserForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roleList", roleRepository.findAll());
        return "/newUser";
    }

    @GetMapping(value = "/admin/updateUserForm")
    public String updateForm(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("roleList", roleRepository.findAll());
        return "/update";
    }

    @PostMapping(value = "/admin/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:";
    }

    @PutMapping(value = "/admin/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:";
    }

    @GetMapping(value = "/admin/delete")
    public String delete(@RequestParam(value = "id") Long id) {
        userService.deleteUserById(id);
        return "redirect:";
    }
}
