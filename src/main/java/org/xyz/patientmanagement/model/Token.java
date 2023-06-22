package org.xyz.patientmanagement.model;

import org.xyz.patientmanagement.domain.Assistant;
import org.xyz.patientmanagement.domain.Doctor;
import org.xyz.patientmanagement.domain.User;

import static org.xyz.patientmanagement.domain.UserType.*;

/**
 * @author inanmashrur
 * @since 19/04/2023
 */
public class Token {

    private User user;

    private Doctor doctor;

    private Assistant assistant;

    private boolean isAdmins;

    private boolean isDoctors;

    private boolean isAssistants;

    private Token () {
        doctor = null;
        assistant = null;
    }

    public Token (User user) {
        this();
        this.user = user;
        this.isAdmins = user.getUserType().equals(ADMIN);
        this.isDoctors = user.getUserType().equals(DOCTOR);
        this.isAssistants = user.getUserType().equals(ASSISTANT);
    }

    public User getUser() {
        return user;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    public boolean isAdmins() {
        return isAdmins;
    }

    public boolean isDoctors() {
        return isDoctors;
    }

    public boolean isAssistants() {
        return isAssistants;
    }
}
