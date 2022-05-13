package org.example.bootstrap5.repositories;

import org.example.bootstrap5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long>{

    User findUserById(Long id);

    @Query("select u FROM User u join fetch u.roles where u.name =:name")
    User findUserByName(@Param("name") String name);

    @Query("select u From User u join fetch u.roles where u.email =:email")
    User findUserByEmail(@Param("email") String email);

}
