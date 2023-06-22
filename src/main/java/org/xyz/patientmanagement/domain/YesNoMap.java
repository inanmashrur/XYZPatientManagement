package org.xyz.patientmanagement.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inanmashrur
 * @since 5/29/2023
 */
public enum YesNoMap {

    YES("Yes", true),

    NO("No", false),

    UNDEFINED("Undefined", null);

    private final String label;

    private final Boolean value;

    YesNoMap(String label, Boolean value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return this.label;
    }
    public Boolean getValue() {
        return this.value;
    }
}
