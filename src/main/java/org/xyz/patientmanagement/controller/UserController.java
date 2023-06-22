package org.xyz.patientmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.xyz.patientmanagement.domain.User;
import org.xyz.patientmanagement.service.UserService;
import org.xyz.patientmanagement.validator.UserValidator;

import static org.xyz.patientmanagement.util.Constants.*;
import static org.xyz.patientmanagement.util.Util.getRequestObject;
import static org.xyz.patientmanagement.util.Util.isValidIdentifier;

/**
 * @author inanmashrur
 * @since 19/04/2023
 */
@Controller
@RequestMapping({"/user"})
@SessionAttributes(COMMAND_USER)
public class UserController {

    private static final String FORM_PAGE = "user/form";

    private static final String LIST_PAGE = "user/list";

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @InitBinder(COMMAND_USER)
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping
    public String show(ModelMap modelMap) {
        modelMap.put(COMMAND_USER, getCommandObject());

        return FORM_PAGE;
    }

    @GetMapping("/list")
    public String showAll(ModelMap modelMap) {
        modelMap.put(USER_LIST, userService.findAll());

        return LIST_PAGE;
    }

    public User getCommandObject() {
        long userId = ServletRequestUtils.getLongParameter(getRequestObject(), USER_ID, DEFAULT);

        if (isValidIdentifier(userId)) {
            return userService.findActiveById(userId);
        }

        return new User();
    }
}
