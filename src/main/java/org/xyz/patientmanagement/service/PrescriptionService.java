package org.xyz.patientmanagement.service;

import org.springframework.stereotype.Repository;
import org.xyz.patientmanagement.domain.Patient;
import org.xyz.patientmanagement.domain.Prescription;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static org.xyz.patientmanagement.domain.Status.ACTIVE;
import static org.xyz.patientmanagement.domain.Status.DELETED;
import static org.xyz.patientmanagement.util.Util.isNonNull;

/**
 * @author inanmashrur
 * @since 6/7/2023
 */
@Repository
public class PrescriptionService {

    @PersistenceContext
    private EntityManager entityManager;

    public Prescription findById(long prescriptionId) {
        return entityManager.find(Prescription.class, prescriptionId);
    }

    public List<Prescription> findAllByPatient(Patient patient) {
        return entityManager.createQuery("From Prescription WHERE patient = :patient", Prescription.class)
                .setParameter("patient", patient)
                .getResultList();
    }

    @Transactional
    public Prescription saveOrUpdate(Prescription prescription) {
        if (!prescription.isNew()) {
            return entityManager.merge(prescription);
        }

        entityManager.persist(prescription);

        return prescription;
    }
}
