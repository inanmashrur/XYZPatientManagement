package org.xyz.patientmanagement.domain;

/**
 * @author inanmashrur
 * @since 26/03/2023
 */
public enum UserType {

    ADMIN("Admin"),
    ASSISTANT("Assistant"),
    DOCTOR("Doctor");

    private final String name;

    UserType(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.name;
    }
}
