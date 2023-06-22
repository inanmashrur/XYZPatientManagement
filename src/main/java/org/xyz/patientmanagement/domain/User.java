package org.xyz.patientmanagement.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static org.xyz.patientmanagement.util.Util.isValidIdentifier;

/**
 * @author inanmashrur
 * @since 26/03/2023
 */
@Entity
@Table(name = "\"user\"")
public class User extends Persistent {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    @SequenceGenerator(name = "userSeq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String username;

    @Size(min = 6)
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User updatedBy;

    public User() {
        status = Status.PENDING;
    }

    public User(UserType userType) {
        this();
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getName() {
        return firstName + " " + lastName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userType=" + userType +
                ", status=" + status +
                '}';
    }
}
