package org.xyz.patientmanagement.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static org.xyz.patientmanagement.util.Util.isValidIdentifier;

/**
 * @author inanmashrur
 * @since 26/03/2023
 */
@Entity
@Table(name = "assistant")
public class Assistant extends Persistent {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assistantSeq")
    @SequenceGenerator(name = "assistantSeq", sequenceName = "assistant_seq", allocationSize = 1)
    private long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    @NotNull
    private User user;

    @ManyToOne
    @NotNull
    private Doctor doctor;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User updatedBy;

    public Assistant() {
        status = Status.PENDING;
    }

    public Assistant(User user, Doctor doctor) {
        this();
        this.user = user;
        this.doctor = doctor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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
        return "Assistant{" +
                "id=" + id +
                ", status=" + status +
                ", user=" + user +
                ", doctor=" + doctor +
                '}';
    }
}
