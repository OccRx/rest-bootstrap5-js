package org.my.bootstrap5.util;

import org.my.bootstrap5.model.Role;
import org.my.bootstrap5.model.User;
import org.my.bootstrap5.service.RoleService;
import org.my.bootstrap5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminInit {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminInit(UserService userService,  RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        try {
            Role role = new Role("ROLE_ADMIN");
            Role role1 = new Role("ROLE_USER");
            roleService.save(role);
            roleService.save(role1);
            List<Role> roles = new ArrayList<>();
            roles.add(role);
            roles.add(role1);
            User admin = new User("admin@gmail.com", "admin", 54, "admin", "123", roles);
            userService.save(admin);
        } catch (RuntimeException ignored){}
    }
}
