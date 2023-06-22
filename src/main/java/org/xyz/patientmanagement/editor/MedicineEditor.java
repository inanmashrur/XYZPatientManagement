package org.xyz.patientmanagement.editor;

import org.springframework.stereotype.Component;
import org.xyz.patientmanagement.domain.YesNoMap;
import org.xyz.patientmanagement.model.Medicine;
import org.xyz.patientmanagement.model.MedicineForm;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author inanmashrur
 * @since 5/28/2023
 */
@Component
public class MedicineEditor extends PropertyEditorSupport  {

    @Override
    public String getAsText() {
        return super.getAsText();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        System.out.println(text);

        List<String> medicineProps = Arrays.stream(text.split("-%-"))
                .map(String::trim)
                .collect(Collectors.toList());

        Medicine medicine = new Medicine();
        medicine.setForm(MedicineForm.valueOf(medicineProps.get(0)));
        medicine.setName(medicineProps.get(1));
        medicine.setDosage(medicineProps.get(2));
        medicine.setOnFullStomach(YesNoMap.valueOf(medicineProps.get(3)));
        medicine.setFrequency(medicineProps.get(4));

        System.out.println(medicine);

        super.setValue(medicine);
    }
}
