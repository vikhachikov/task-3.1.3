package ru.itmentor.spring.boot_security.demo.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    public UserDaoImp(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUserById(long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public List<User> getUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    public Set<Role> getRoles() {
        TypedQuery<Role> query = entityManager.createQuery("SELECT u FROM Role u", Role.class);
        return new HashSet<>(query.getResultList());
    }

    @Override
    public User getUserById(long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public Role getRoleById(long id) {
        Role role = entityManager.find(Role.class, id);
        return role;
    }

    @Override
    public void updateUser(long id, User updatedUser) {
        User user = getUserById(id);
        if (user != null) {
            user.setRoles(updatedUser.getRoles());
            user.setLogin(updatedUser.getLogin());
            user.setName(updatedUser.getName());
            user.setSurname(updatedUser.getSurname());
            user.setAge(updatedUser.getAge());
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
    }

    @Override
    public User findByLogin(String login) {
        String query = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.login = :login";
        return entityManager.createQuery(query, User.class)
                .setParameter("login", login)
                .getSingleResult();
    }
}
