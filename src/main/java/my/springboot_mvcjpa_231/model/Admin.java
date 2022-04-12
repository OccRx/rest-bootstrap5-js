package my.springboot_mvcjpa_231.model;

import my.springboot_mvcjpa_231.DAO.RoleRepo;
import my.springboot_mvcjpa_231.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Admin {

    private  UserService usersService;
    private  RoleRepo roleRepo;

    public Admin(UserService usersService, RoleRepo roleRepo) {
        this.usersService = usersService;
        this.roleRepo = roleRepo;
    }

    @PostConstruct
    public void init() {
        Role role = new Role("ROLE_ADMIN");
        Role role1 = new Role("ROLE_USER");
        roleRepo.save(role);
        roleRepo.save(role1);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        roles.add(role1);
        User admin = new User("admin", 54, "admin", "123", roles);
        usersService.add(admin);
    }
}
