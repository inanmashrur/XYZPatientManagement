package org.xyz.patientmanagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xyz.patientmanagement.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static java.security.MessageDigest.isEqual;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.xyz.patientmanagement.util.Util.getHashedValue;

/**
 * @author inanmashrur
 * @since 04/04/2023
 */
@Repository
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserService userService;

    public User getAuthenticateUser(User user) {
        try {
            User activeUser = userService.findActiveByUsername(user.getUsername());

            if (isNull(activeUser)) {
                return null;
            }

            String hashedPassword = getHashedValue(user.getPassword(), ofNullable(activeUser.getSalt()).orElse(""));

            if (!isEqual(activeUser.getPassword().getBytes(), hashedPassword.getBytes())) {
                return null;
            }

            return activeUser;
        } catch (Exception e) {
            logger.error("[AuthService::authenticate]", e);
            return null;
        }
    }

}
