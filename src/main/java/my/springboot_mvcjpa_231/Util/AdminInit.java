package my.springboot_mvcjpa_231.Util;

import my.springboot_mvcjpa_231.Repositories.RoleRepository;
import my.springboot_mvcjpa_231.model.Role;
import my.springboot_mvcjpa_231.model.User;
import my.springboot_mvcjpa_231.service.UserService;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminInit {

    private UserService userService;
    private RoleRepository roleRepository;

    public AdminInit(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        Role role = new Role("ROLE_ADMIN");
        Role role1 = new Role("ROLE_USER");
        roleRepository.save(role);
        roleRepository.save(role1);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        roles.add(role1);
        User admin = new User("admin", 54, "admin", "123", roles);
        userService.save(admin);
    }
}
