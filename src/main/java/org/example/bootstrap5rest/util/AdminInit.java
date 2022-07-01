package org.example.bootstrap5rest.util;

import org.example.bootstrap5rest.model.Role;
import org.example.bootstrap5rest.model.User;
import org.example.bootstrap5rest.service.RoleService;
import org.example.bootstrap5rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
public class AdminInit {

    private final UserService userService;
    private final RoleService roleService;


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
