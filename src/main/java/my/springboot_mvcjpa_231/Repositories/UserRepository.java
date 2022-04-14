package my.springboot_mvcjpa_231.Repositories;

import my.springboot_mvcjpa_231.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long>{

    User findUserById(Long id);

    @Query("select u FROM User u left join fetch u.roles where u.name =:name")
    User findUserByName(@Param("name") String name);

}
