package org.xyz.patientmanagement.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xyz.patientmanagement.domain.User;
import org.xyz.patientmanagement.model.Token;
import org.xyz.patientmanagement.service.DoctorService;

import javax.servlet.http.HttpSession;

import static org.xyz.patientmanagement.util.Constants.TOKEN;
import static org.xyz.patientmanagement.util.Util.getRequestObject;

/**
 * @author inanmashrur
 * @since 19/04/2023
 */
@Component
public class AuthHelper {

    @Autowired
    private DoctorService doctorService;

    public void setUpSessionData(User user) {
        Token token = new Token(user);

        if (token.isDoctors()) {
            token.setDoctor(doctorService.findByUser(user));
        } else if (token.isAssistants()) {
            //TODO: need to set Assistant
        }

        getRequestObject().getSession().setAttribute(TOKEN, token);
    }

    public void removeSessionData(HttpSession session) {
        session.removeAttribute(TOKEN);

        session.invalidate();
    }
}
