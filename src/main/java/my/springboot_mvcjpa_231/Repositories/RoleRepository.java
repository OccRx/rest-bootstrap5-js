package my.springboot_mvcjpa_231.Repositories;

import my.springboot_mvcjpa_231.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByRoleName(String roleName);

    List<Role> findAll();

}
