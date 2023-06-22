package org.xyz.patientmanagement.domain;

/**
 * @author inanmashrur
 * @since 04/04/2023
 */
public enum Status {

    ACTIVE("Active"),

    DELETED("Deleted"),

    PENDING("Pending");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
