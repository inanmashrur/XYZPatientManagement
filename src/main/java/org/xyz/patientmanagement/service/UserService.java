package org.xyz.patientmanagement.service;

import org.xyz.patientmanagement.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static org.xyz.patientmanagement.domain.Status.*;
import static org.xyz.patientmanagement.util.Util.isNonNull;

/**
 * @author inanmashrur
 * @since 04/04/2023
 */
@Repository
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean isExists(User user) {
        return entityManager
                .createQuery("From User Where username = :username", User.class)
                .setParameter("username", user.getUsername())
                .getResultList()
                .stream()
                .findFirst()
                .map(u -> !u.equals(user))
                .orElse(false);
    }

    public User findById(long userId) {
        return entityManager.find(User.class, userId);
    }

    public User findActiveById(long userId) {
        User user = findById(userId);

        return isNonNull(user) && user.getStatus() == ACTIVE ? user : null;
    }

    public List<User> findAll() {
        return entityManager.createQuery("From User", User.class).getResultList();
    }

    @Transactional
    public User saveOrUpdate(User user) {
        if (!user.isNew()) {
            return entityManager.merge(user);
        }

        entityManager.persist(user);

        return user;
    }

    @Transactional
    public void delete(long userId) {
        entityManager
                .createQuery("UPDATE User SET status = " + DELETED + "  WHERE id = :id")
                .setParameter("id", userId)
                .executeUpdate();
    }
}
