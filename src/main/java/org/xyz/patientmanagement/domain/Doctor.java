package org.xyz.patientmanagement.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import static org.xyz.patientmanagement.util.Util.isValidIdentifier;

/**
 * @author inanmashrur
 * @since 26/03/2023
 */
@Entity
@Table(name = "doctor")
public class Doctor extends Persistent {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctorSeq")
    @SequenceGenerator(name = "doctorSeq", sequenceName = "doctor_seq", allocationSize = 1)
    private Long id;

    @NotNull
    private String education;

    @NotNull
    private String specialities;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    @NotNull
    private User user;

    @OneToMany(mappedBy = "doctor")
    private Set<Assistant> assistants;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User updatedBy;

    public Doctor() {
        assistants = new HashSet<>();
        status = Status.PENDING;
    }

    public Doctor(User user) {
        this();
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSpecialities() {
        return specialities;
    }

    public void setSpecialities(String specialities) {
        this.specialities = specialities;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Assistant> getAssistants() {
        return assistants;
    }

    public void setAssistants(Set<Assistant> assistants) {
        this.assistants = assistants;
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
        return "Doctor{" +
                "id=" + id +
                ", education='" + education + '\'' +
                ", specialities='" + specialities + '\'' +
                ", status=" + status +
                ", user=" + user +
                '}';
    }
}
