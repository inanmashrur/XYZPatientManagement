package org.xyz.patientmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.UriComponentsBuilder;
import org.xyz.patientmanagement.domain.BloodGroup;
import org.xyz.patientmanagement.domain.Patient;
import org.xyz.patientmanagement.domain.Prescription;
import org.xyz.patientmanagement.domain.User;
import org.xyz.patientmanagement.service.PatientService;
import org.xyz.patientmanagement.service.PrescriptionService;
import org.xyz.patientmanagement.validator.PatientValidator;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.xyz.patientmanagement.util.Constants.*;
import static org.xyz.patientmanagement.util.Util.*;

/**
 * @author inanmashrur
 * @since 20/04/2023
 */
@Controller
@RequestMapping("/patient")
@SessionAttributes({COMMAND_PATIENT})
public class PatientController {

    private static final String FORM_PAGE = "patient/form";

    private static final String LIST_PAGE = "patient/list";

    @Autowired
    private PatientService patientService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private PatientValidator patientValidator;

    @InitBinder(COMMAND_PATIENT)
    public void initBinderForDoctor(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(DATE_FORMAT), true));
    }

    @GetMapping("/form")
    public String show(ModelMap modelMap) {
        setUpReferenceData(modelMap);

        return FORM_PAGE;
    }

    @PostMapping("/form")
    public String saveOrUpdate(@Valid @ModelAttribute(COMMAND_PATIENT) Patient patient,
                               BindingResult bindingResult,
                               SessionStatus sessionStatus,
                               ModelMap modelMap) {

        patientValidator.validate(patient, bindingResult);

        if (bindingResult.hasErrors()) {
            setUpReferenceData(modelMap);
            return FORM_PAGE;
        }

        prepareForSaveOrUpdate(patient);
        patientService.saveOrUpdate(patient);
        sessionStatus.setComplete();

        return redirectTo(LIST_PAGE);
    }

    @GetMapping("/list")
    public String showAll(ModelMap modelMap) {
        modelMap.put(PATIENT_LIST, patientService.findAll());

        return LIST_PAGE;
    }

    public void prepareForSaveOrUpdate(Patient patient) {
        User loggedInUser = getLoggedInUser();

        if (patient.isNew()) {
            patient.setCreatedBy(loggedInUser);
            return;
        }

        patient.setUpdatedBy(loggedInUser);
    }

    private void setUpReferenceData(ModelMap modelMap) {
        long patientId = ServletRequestUtils.getLongParameter(getRequestObject(), PATIENT_ID, DEFAULT);
        Patient patient;
        List<Prescription> prescriptionList = new ArrayList<>();

        modelMap.put(BLOOD_GROUP_LIST, BloodGroup.getBloodGroupList());

        if (isValidIdentifier(patientId)) {
            patient = patientService.findActiveById(patientId);
            prescriptionList = prescriptionService.findAllByPatient(patient);
            String newPrescriptionURL = getNewPrescriptionURL(patientId);

            modelMap.put(COMMAND_PATIENT, patient);
            modelMap.put(PRESCRIPTION_LIST, prescriptionList);
            modelMap.put("addNewPrescriptionLink", newPrescriptionURL);

            return;
        }

        modelMap.put(COMMAND_PATIENT, new Patient());
        modelMap.put(PRESCRIPTION_LIST, prescriptionList);
    }

    private static String getNewPrescriptionURL(long patientId) {
        Long doctorId = getLoggedInDoctor().getId();

        return UriComponentsBuilder.fromPath("/prescription/form")
                .queryParam("patientId", patientId)
                .queryParam("doctorId", doctorId)
                .toUriString();
    }

}
