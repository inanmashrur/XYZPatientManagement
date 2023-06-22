package org.xyz.patientmanagement.validator;

import org.xyz.patientmanagement.domain.User;
import org.xyz.patientmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.Objects.isNull;
import static org.xyz.patientmanagement.util.Constants.DATE_FORMAT;

/**
 * @author inanmashrur
 * @since 02/02/2023
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        User user = (User) target;

        if (userService.isExists(user)) {
            errors.rejectValue("username", "error.exists");
        }
    }
}
