package org.xyz.patientmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xyz.patientmanagement.domain.User;

import javax.servlet.http.HttpSession;

import static org.xyz.patientmanagement.util.Constants.*;
import static org.xyz.patientmanagement.util.Util.*;

/**
 * @author inanmashrur
 * @since 04/04/2023
 */
@Controller
public class AppController {

    private static final String HOME_PAGE = "home";

    private static final String LOGIN_PAGE = "login";

    private static final String MSG_PAGE = "msg";

    @GetMapping(value = {"/", "/login"})
    public String login(ModelMap modelMap, HttpSession session) {
//        if (isLoggedIn(session)) {
//            return HOME_PAGE;
//        }

        modelMap.addAttribute(COMMAND_USER, new User());

        return LOGIN_PAGE;
    }

    @GetMapping(value = "home")
    public String home() {
        return HOME_PAGE;
    }

    @GetMapping(value = "msg")
    public String showMsg(@RequestParam(required = false) String type,
                          @RequestParam(required = false) String msg,
                          ModelMap modelMap) {

        if (isNonNull(type, msg)) {
            modelMap.put(TYPE, type);
            modelMap.put(MSG, msg);
        }

        return MSG_PAGE;
    }
}
