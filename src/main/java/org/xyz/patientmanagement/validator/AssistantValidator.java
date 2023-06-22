package org.xyz.patientmanagement.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.xyz.patientmanagement.domain.Assistant;
import org.xyz.patientmanagement.domain.User;
import org.xyz.patientmanagement.service.UserService;

/**
 * @author inanmashrur
 * @since 02/02/2023
 */
@Component
public class AssistantValidator implements Validator {

    @Autowired
    private UserValidator userValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return Assistant.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        Assistant assistant = (Assistant) target;

        userValidator.validate(assistant.getUser(), errors);
    }
}
