package org.xyz.patientmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.xyz.patientmanagement.domain.Assistant;
import org.xyz.patientmanagement.domain.User;
import org.xyz.patientmanagement.domain.UserType;
import org.xyz.patientmanagement.service.AssistantService;
import org.xyz.patientmanagement.validator.UserValidator;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

import static org.xyz.patientmanagement.util.Constants.*;
import static org.xyz.patientmanagement.util.Util.*;

/**
 * @author inanmashrur
 * @since 20/04/2023
 */
@Controller
@RequestMapping("/assistant")
@SessionAttributes({COMMAND_ASSISTANT})
public class AssistantController {

    private static final String FORM_PAGE = "assistant/form";

    private static final String LIST_PAGE = "assistant/list";

    @Autowired
    private AssistantService assistantService;

    @Autowired
    private UserValidator userValidator;

    @InitBinder(COMMAND_ASSISTANT)
    public void initBinderForDoctor(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/form")
    public String show(ModelMap modelMap) {
        modelMap.put(COMMAND_ASSISTANT, getCommandObject());

        return FORM_PAGE;
    }

    @PostMapping("/form")
    public String saveOrUpdate(@ModelAttribute(COMMAND_ASSISTANT) Assistant assistant,
                               BindingResult bindingResult,
                               SessionStatus sessionStatus) throws NoSuchAlgorithmException {

        userValidator.validate(assistant.getUser(), bindingResult);

        if (bindingResult.hasErrors()) {
            return FORM_PAGE;
        }

        assistant.getUser().setPassword(getHashedValue(assistant.getUser().getPassword()));
        prepareForSaveOrUpdate(assistant);

        assistantService.saveOrUpdate(assistant);
        sessionStatus.setComplete();

        return redirectTo(LIST_PAGE);
    }

    @GetMapping("/list")
    public String showAll(ModelMap modelMap) {
        modelMap.put(ASSISTANT_LIST, assistantService.findAll());

        return LIST_PAGE;
    }

    public Assistant getCommandObject() {
        long assistantId = ServletRequestUtils.getLongParameter(getRequestObject(), ASSISTANT_ID, DEFAULT);

        if (isValidIdentifier(assistantId)) {
            return assistantService.findActiveById(assistantId);
        }

        return new Assistant(new User(UserType.ASSISTANT), getLoggedInDoctor());
    }

    public void prepareForSaveOrUpdate(Assistant assistant) {
        User loggedInUser = getLoggedInUser();

        if (assistant.isNew()) {
            assistant.setCreatedBy(loggedInUser);
            assistant.getUser().setCreatedBy(loggedInUser);
            return;
        }

        assistant.getUser().setUpdatedBy(loggedInUser);
        assistant.setUpdatedBy(loggedInUser);
    }
}
