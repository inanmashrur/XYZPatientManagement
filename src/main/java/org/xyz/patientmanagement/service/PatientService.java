package org.xyz.patientmanagement.service;

import org.springframework.stereotype.Repository;
import org.xyz.patientmanagement.domain.Patient;
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
public class PatientService {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean isExists(Patient patient) {
        return entityManager
                .createQuery("From Patient Where mobileNumber = :mobileNumber", Patient.class)
                .setParameter("mobileNumber", patient.getMobileNumber())
                .getResultList()
                .stream()
                .findFirst()
                .map(p -> !p.equals(patient))
                .orElse(false);
    }

    public Patient findById(long patientId) {
        return entityManager.find(Patient.class, patientId);
    }
    
    public Patient findActiveById(long patientId) {
        Patient patient = findById(patientId);

        return isNonNull(patient) && patient.getStatus() == ACTIVE ? patient : null;
    }

    public List<Patient> findAll() {
        return entityManager.createQuery("From Patient", Patient.class).getResultList();
    }

    @Transactional
    public Patient saveOrUpdate(Patient patient) {
        if (!patient.isNew()) {
            return entityManager.merge(patient);
        }

        entityManager.persist(patient);

        return patient;
    }

    @Transactional
    public void delete(long patientId) {
        entityManager
                .createQuery("UPDATE Patient SET status = " + DELETED + "  WHERE id = :id")
                .setParameter("id", patientId)
                .executeUpdate();
    }
}
