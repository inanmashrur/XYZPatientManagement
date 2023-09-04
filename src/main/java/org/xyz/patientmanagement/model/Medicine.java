package org.xyz.patientmanagement.model;

import org.xyz.patientmanagement.domain.YesNoMap;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author inanmashrur
 * @since 26/03/2023
 */
public class Medicine {

    @NotNull
    private MedicineForm form;

    @NotBlank
    private String name;

    @Size(max = 255)
    private String dosage;

    @Enumerated(EnumType.STRING)
    @NotNull
    private YesNoMap onFullStomach;

    @Size(max = 255)
    private String frequency;

    public MedicineForm getForm() {
        return form;
    }

    public void setForm(MedicineForm form) {
        this.form = form;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public YesNoMap getOnFullStomach() {
        return onFullStomach;
    }

    public void setOnFullStomach(YesNoMap inFullStomach) {
        onFullStomach = inFullStomach;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "form=" + form +
                ", name='" + name + '\'' +
                ", dosage='" + dosage + '\'' +
                ", isInFullStomach=" + onFullStomach +
                ", frequency='" + frequency + '\'' +
                '}';
    }
}
