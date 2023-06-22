package org.xyz.patientmanagement.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;
import org.xyz.patientmanagement.model.Medicine;
import org.xyz.patientmanagement.model.Test;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static org.xyz.patientmanagement.util.Util.isValidIdentifier;

/**
 * @author inanmashrur
 * @since 26/03/2023
 */
@Entity
@Table(name = "prescription")
public class Prescription extends Persistent {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prescriptionSeq")
    @SequenceGenerator(name = "prescriptionSeq", sequenceName = "prescription_seq", allocationSize = 1)
    private long id;

    @Transient
    private List<Medicine> medicineList;

    @Column(name = "medicines")
    private String medicinesInJson;

    @Transient
    private List<Test> testList;

    @Column(name = "tests")
    private String testsInJson;

    @OneToOne(cascade = CascadeType.ALL)
    private HealthMetric healthMetric;

    @ManyToOne
    @NotNull
    private Doctor doctor;

    @ManyToOne
    @NotNull
    private Patient patient;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "prescription_id", updatable = false, nullable = false)
    private List<Attachment> attachments;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User updatedBy;

    public Prescription() {
        medicineList = new ArrayList<>();
        testList = new ArrayList<>();
        attachments = new ArrayList<>();
    }

    public Prescription(Patient patient, Doctor doctor) {
        this();
        this.patient = patient;
        this.doctor = doctor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    public String getMedicinesInJson() {
        return medicinesInJson;
    }

    public void setMedicinesInJson(String medicinesInJson) {
        this.medicinesInJson = medicinesInJson;
    }

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    public String getTestsInJson() {
        return testsInJson;
    }

    public void setTestsInJson(String testsInJson) {
        this.testsInJson = testsInJson;
    }

    public HealthMetric getHealthMetric() {
        return healthMetric;
    }

    public void setHealthMetric(HealthMetric healthMetric) {
        this.healthMetric = healthMetric;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean isNew() {
        return !isValidIdentifier(id);
    }
}
