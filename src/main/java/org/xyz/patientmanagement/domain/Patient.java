package org.xyz.patientmanagement.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

import static org.xyz.patientmanagement.domain.Status.ACTIVE;
import static org.xyz.patientmanagement.util.Util.isValidIdentifier;

/**
 * @author inanmashrur
 * @since 26/03/2023
 */
@Entity
@Table(name = "patient")
public class Patient extends Persistent {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patientSeq")
    @SequenceGenerator(name = "patientSeq", sequenceName = "patient_seq", allocationSize = 1)
    private Long id;

    private String patientId;

    @NotNull
    private String mobileNumber;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User updatedBy;

    public Patient() {
        status = ACTIVE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return firstName + " " + lastName;
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

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", patientId='" + patientId + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", bloodGroup=" + bloodGroup +
                '}';
    }
}
