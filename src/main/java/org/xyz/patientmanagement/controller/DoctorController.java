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
import org.xyz.patientmanagement.domain.Doctor;
import org.xyz.patientmanagement.domain.User;
import org.xyz.patientmanagement.domain.UserType;
import org.xyz.patientmanagement.helper.UserHelper;
import org.xyz.patientmanagement.service.DoctorService;
import org.xyz.patientmanagement.validator.UserValidator;

import javax.validation.Valid;

import static org.xyz.patientmanagement.util.Constants.*;
import static org.xyz.patientmanagement.util.Util.*;

/**
 * @author inanmashrur
 * @since 20/04/2023
 */
@Controller
@RequestMapping("/doctor")
@SessionAttributes({COMMAND_DOCTOR})
public class DoctorController {

    private static final String FORM_PAGE_1 = "doctor/basicInfo";

    private static final String FORM_PAGE_2 = "doctor/doctorInfo";

    private static final String FORM_PAGE_PREVIEW = "doctor/preview";

    private static final String LIST_PAGE = "doctor/list";

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserHelper userHelper;

    @InitBinder(COMMAND_DOCTOR)
    public void initBinderForDoctor(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/basicInfo")
    public String getBasicInfo(ModelMap modelMap) {
        modelMap.put(COMMAND_DOCTOR, getCommandObject());

        return FORM_PAGE_1;
    }

    @PostMapping("/basicInfo")
    public String saveBasicInfo(@ModelAttribute(COMMAND_DOCTOR) Doctor doctor, BindingResult bindingResult) {

        userValidator.validate(doctor.getUser(), bindingResult);

        if (bindingResult.hasErrors()) {
            return FORM_PAGE_1;
        }

        return redirectTo(FORM_PAGE_2);
    }

    @GetMapping("/doctorInfo")
    public String getDoctorInfo(@ModelAttribute(COMMAND_DOCTOR) Doctor doctor) {
        prepareForSaveOrUpdate(doctor);

        return FORM_PAGE_2;
    }

    @PostMapping("/doctorInfo")
    public String saveDoctorInfo(@Valid @ModelAttribute(COMMAND_DOCTOR) Doctor doctor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return FORM_PAGE_2;
        }

        return redirectTo(FORM_PAGE_PREVIEW);
    }

    @GetMapping("/preview")
    public String showPreview(@ModelAttribute(COMMAND_DOCTOR) Doctor doctor) {
        return FORM_PAGE_PREVIEW;
    }

    @PostMapping("/preview")
    public String saveOrUpdate(@Valid @ModelAttribute(COMMAND_DOCTOR) Doctor doctor,
                               BindingResult bindingResult,
                               SessionStatus sessionStatus) {

        if (bindingResult.hasErrors()) {
            return FORM_PAGE_PREVIEW;
        }

        doctorService.saveOrUpdate(doctor);
        sessionStatus.setComplete();

        return redirectTo("home");
    }

    @GetMapping("/list")
    public String showAll(ModelMap modelMap) {
        modelMap.put(DOCTOR_LIST, doctorService.findAll());

        return LIST_PAGE;
    }

    public Doctor getCommandObject() {
        long doctorId = ServletRequestUtils.getLongParameter(getRequestObject(), DOCTOR_ID, DEFAULT);

        if (isValidIdentifier(doctorId)) {
            return doctorService.findActiveById(doctorId);
        }

        return new Doctor(new User(UserType.DOCTOR));
    }

    public void prepareForSaveOrUpdate(Doctor doctor) {
        User loggedInUser = getLoggedInUser();

        if (doctor.isNew()) {
            userHelper.initializeCredentials(doctor.getUser(), doctor.getUser().getPassword());
            doctor.setCreatedBy(loggedInUser);
            doctor.getUser().setCreatedBy(loggedInUser);

            return;
        }

        doctor.getUser().setUpdatedBy(loggedInUser);
        doctor.setUpdatedBy(loggedInUser);
    }

}
