package org.xyz.patientmanagement.helper;

import org.springframework.stereotype.Component;
import org.xyz.patientmanagement.domain.User;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.xyz.patientmanagement.util.Util.generateSalt;
import static org.xyz.patientmanagement.util.Util.getHashedValue;

@Component
public class UserHelper {

    public void initializeCredentials(User user, String password) {
        if (isNull(password)) {
            throw new IllegalArgumentException("Password can not be null");
        }

        if (isNull(user.getSalt()) && user.isNew()) {
            user.setSalt(generateSalt());
        }

        user.setPassword(getHashedValue(password, ofNullable(user.getSalt()).orElse("")));
    }

}
