package org.xyz.patientmanagement.domain;

/**
 * @author inanmashrur
 * @since 30/03/2023
 */
public enum AttachmentType {

    IMAGE("Image"),
    PDF("PDF");

    private final String name;

    AttachmentType(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.name;
    }
}
