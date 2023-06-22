package org.xyz.patientmanagement.domain;

import java.util.Arrays;
import java.util.List;

/**
 * @author inanmashrur
 * @since 29/01/2023
 */
public enum BloodGroup {

    UNKNOWN("Unknown"),

    A_POS("A+"),

    A_NEG("A-"),

    B_POS("B+"),

    B_NEG("B-"),

    AB_POS("AB+"),

    AB_NEG("AB-"),

    O_POS("O+"),

    O_NEG("O-");

    private final String value;

    BloodGroup(String value) {
        this.value = value;
    }

    public static List<BloodGroup> getBloodGroupList() {
        return Arrays.asList(values());
    }

    public String getValue() {
        return this.value;
    }
}
