package org.xyz.patientmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xyz.patientmanagement.domain.Assistant;
import org.xyz.patientmanagement.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static org.xyz.patientmanagement.domain.Status.ACTIVE;
import static org.xyz.patientmanagement.domain.Status.DELETED;
import static org.xyz.patientmanagement.util.Util.isNonNull;

/**
 * @author inanmashrur
 * @since 04/04/2023
 */
@Repository
public class AssistantService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserService userService;

    public Assistant findById(long assistantId) {
        return entityManager.find(Assistant.class, assistantId);
    }

    public Assistant findByUser(User user) {
        return entityManager.createQuery("From Assistant WHERE user = :user", Assistant.class)
                .setParameter("user", user)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public Assistant findActiveById(long assistantId) {
        Assistant assistant = findById(assistantId);

        return isNonNull(assistant) && assistant.getStatus() == ACTIVE ? assistant : null;
    }

    public List<Assistant> findAll() {
        return entityManager.createQuery("From Assistant", Assistant.class).getResultList();
    }

    @Transactional
    public Assistant saveOrUpdate(Assistant assistant) {
        if (!assistant.isNew()) {
            return entityManager.merge(assistant);
        }

        userService.saveOrUpdate(assistant.getUser());
        entityManager.persist(assistant);

        return assistant;
    }

    @Transactional
    public void delete(long doctorId) {
        entityManager
                .createQuery("UPDATE Doctor SET status = " + DELETED + "  WHERE id = :id")
                .setParameter("id", doctorId)
                .executeUpdate();
    }
}
