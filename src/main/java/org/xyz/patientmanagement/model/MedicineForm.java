package org.xyz.patientmanagement.model;

/**
 * @author inanmashrur
 * @since 31/03/2023
 */
public enum MedicineForm {

    TAB("Tablet"),
    CAP("Capsule"),
    SYP("Syrup"),
    DRP("Drop"),
    CRM("Cream");

    private final String name;

    MedicineForm(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
