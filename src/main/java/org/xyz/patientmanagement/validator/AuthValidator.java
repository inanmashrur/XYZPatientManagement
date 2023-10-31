package org.xyz.patientmanagement.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.xyz.patientmanagement.domain.User;
import org.xyz.patientmanagement.service.AuthService;

import static java.util.Objects.isNull;
import static org.xyz.patientmanagement.util.Constants.PASSWORD;
import static org.xyz.patientmanagement.util.Constants.USERNAME;

/**
 * @author inanmashrur
 * @since 04/04/2023
 */
@Component
public class AuthValidator implements Validator {

    @Autowired
    private AuthService authService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (isNull(user.getUsername())) {
            errors.rejectValue(USERNAME, "error.null");
        }

        if (isNull(user.getPassword())) {
            errors.rejectValue(PASSWORD, "error.null");
        }

        if (!errors.hasErrors() && isNull(authService.getAuthenticateUser((User) target))) {
            errors.reject("error.auth");
        }
    }
}
