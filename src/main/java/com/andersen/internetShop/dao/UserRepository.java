package com.andersen.internetShop.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public User getByLoginAndPassword(String login, String password) {
        String hash = passwordEncoder.encode(password);
        User user = null;

        try {
            user = jdbcTemplate.queryForObject(
                    "select * from customers where login = ? and password = ?",
                    new Object[] {login, passwordEncoder.encode(password)},
                    (rs, i) -> new User(rs.getLong("id"), login, hash)
            );
        } catch (Exception e) {
            log.error("User get by login and password not found: {}", e.getMessage());
        }

        return user;
    }

    public void create(String login, String password) {
        String hash = passwordEncoder.encode(password);

        try {
            jdbcTemplate.update(
                    "insert into customers (login, password) values (?, ?)",
                    login,
                    hash
            );
        } catch (Exception e) {
            log.error("User create exception: {}", e.getMessage());
        }
    }

    public UserDetails getByLogin(String login) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernate");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        User user = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> userRoot = cq.from(User.class);
            cq.select(userRoot);
            cq.where(cb.equal(userRoot.get("login"), login));

            user = em.createQuery(cq).getSingleResult();

            em.getTransaction().commit();
        } catch (Exception e) {
            log.error("User get by login  not found: {}", e.getMessage());
            em.getTransaction().rollback();
        }

        return user;
    }
}
