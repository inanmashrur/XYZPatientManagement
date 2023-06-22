package org.xyz.patientmanagement.formatter;

import org.xyz.patientmanagement.domain.User;
import org.xyz.patientmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

import static org.xyz.patientmanagement.util.Util.isParsableIdentifier;

/**
 * @author inanmashrur
 * @since 04/02/2023
 */
@Component
public class UserFormatter implements Formatter<User> {

    @Autowired
    private UserService userService;

    @Override
    public User parse(String text, Locale locale) {
        return isParsableIdentifier(text) ? userService.findActiveById(Long.parseLong(text)) : null;
    }

    @Override
    public String print(User user, Locale locale) {
        return user.getName();
    }
}
