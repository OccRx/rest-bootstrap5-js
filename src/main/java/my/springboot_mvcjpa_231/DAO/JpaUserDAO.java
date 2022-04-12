package my.springboot_mvcjpa_231.DAO;

import my.springboot_mvcjpa_231.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository(value = "userDAO")
public class JpaUserDAO implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select u from User u",User.class).getResultList();
    }

    @Override
    public User findUserById(long id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(findUserById(id));
    }

    @Override
    public User findUserByName(String name) {
        TypedQuery<User> tq = entityManager.createQuery("SELECT u FROM User u WHERE u.name = :name",User.class);
        tq.setParameter("name", name);
        return tq.getSingleResult();
    }

}
