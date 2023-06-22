package org.xyz.patientmanagement.service;

import org.xyz.patientmanagement.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author inanmashrur
 * @since 04/04/2023
 */
@Repository
public class AuthService {

    @PersistenceContext
    private EntityManager entityManager;

    public User getAuthenticateUser(User user) {
        return entityManager
                .createQuery("FROM User WHERE username = :username AND password = :password", User.class)
                .setParameter("username", user.getUsername())
                .setParameter("password", user.getPassword())
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }
}
