package org.xyz.patientmanagement.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.xyz.patientmanagement.domain.Assistant;
import org.xyz.patientmanagement.domain.Patient;
import org.xyz.patientmanagement.service.PatientService;

/**
 * @author inanmashrur
 * @since 02/02/2023
 */
@Component
public class PatientValidator implements Validator {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private PatientService patientService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Patient.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        Patient patient = (Patient) target;

        if (patientService.isExists(patient)) {
            errors.rejectValue("mobileNumber", "error.exists");
        }
    }
}
