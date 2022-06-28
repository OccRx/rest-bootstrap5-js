package org.example.bootstrap5rest.repositories;

import org.example.bootstrap5rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long>{

    @Query("select u From User u join fetch u.roles where u.email =:email")
    User findUserByEmail(@Param("email") String email);

}
