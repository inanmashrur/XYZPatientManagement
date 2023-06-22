package org.xyz.patientmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xyz.patientmanagement.domain.Doctor;
import org.xyz.patientmanagement.domain.User;

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
public class DoctorService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserService userService;

//    public boolean isExists(User user) {
//        return entityManager
//                .createQuery("From User Where username = :username", User.class)
//                .setParameter("username", user.getUsername())
//                .getResultList()
//                .stream()
//                .findFirst()
//                .map(u -> !u.equals(user))
//                .orElse(false);
//    }

    public Doctor findById(long doctorId) {
        return entityManager.find(Doctor.class, doctorId);
    }

    public Doctor findByUser(User user) {
        return entityManager.createQuery("From Doctor WHERE user = :user", Doctor.class)
                .setParameter("user", user)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public Doctor findActiveById(long doctorId) {
        Doctor doctor = findById(doctorId);

        return isNonNull(doctor) && doctor.getStatus() == ACTIVE ? doctor : null;
    }

    public List<Doctor> findAll() {
        return entityManager.createQuery("From Doctor", Doctor.class).getResultList();
    }

    @Transactional
    public Doctor saveOrUpdate(Doctor doctor) {
        if (!doctor.isNew()) {
            return entityManager.merge(doctor);
        }

        userService.saveOrUpdate(doctor.getUser());
        entityManager.persist(doctor);

        return doctor;
    }

    @Transactional
    public void delete(long doctorId) {
        entityManager
                .createQuery("UPDATE Doctor SET status = " + DELETED + "  WHERE id = :id")
                .setParameter("id", doctorId)
                .executeUpdate();
    }
}
