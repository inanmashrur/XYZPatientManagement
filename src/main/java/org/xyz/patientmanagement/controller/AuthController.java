package org.xyz.patientmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.xyz.patientmanagement.domain.User;
import org.xyz.patientmanagement.helper.AuthHelper;
import org.xyz.patientmanagement.service.AuthService;
import org.xyz.patientmanagement.validator.AuthValidator;

import javax.servlet.http.HttpSession;

import static java.util.Objects.nonNull;
import static org.xyz.patientmanagement.util.Constants.COMMAND_USER;
import static org.xyz.patientmanagement.util.Constants.REDIRECT_TO;
import static org.xyz.patientmanagement.util.Util.getLoggedInUser;

/**
 * @author inanmashrur
 * @since 04/04/2023
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private static final String HOME = "home";

    private static final String LOGIN = "login";

    @Autowired
    private AuthValidator authValidator;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthHelper authHelper;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        logger.info("Logging out | User: {}", getLoggedInUser().getUsername());

        authHelper.removeSessionData(session);

        return REDIRECT_TO + LOGIN;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(COMMAND_USER) User user,
                        BindingResult bindingResult) {

        authValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            logger.warn("Failed login attempt | {}", nonNull(user.getUsername()) ? user.getUsername() : "");

            return LOGIN;
        }

        User loggedInUser = authService.getAuthenticateUser(user);
        authHelper.setUpSessionData(loggedInUser);

        logger.info("New login | User: {}", loggedInUser.getUsername());

        return REDIRECT_TO + HOME;
    }

}
