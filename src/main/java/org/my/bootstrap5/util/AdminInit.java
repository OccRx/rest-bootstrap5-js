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
        if (roleService.findByRoleName("ROLE_ADMIN") == null) {
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleUser = new Role("ROLE_USER");
            roleService.save(roleAdmin);
            roleService.save(roleUser);
            List<Role> roles = new ArrayList<>();
            List<Role> rolesUser = new ArrayList<>();
            rolesUser.add(roleUser);
            roles.add(roleAdmin);
            roles.add(roleUser);
            User admin = new User("admin@gmail.com", "admin", 54, "admin", "123", roles);
            User user = new User("user@gmail.com", "user", 54, "userov", "123", rolesUser);
            User user2 = new User("user1@gmail.com", "user1", 54, "userov", "123", rolesUser);
            userService.save(admin);
            userService.save(user);
            userService.save(user2);
        }
    }
}
