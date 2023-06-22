package org.xyz.patientmanagement.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.xyz.patientmanagement.domain.Doctor;
import org.xyz.patientmanagement.domain.Patient;
import org.xyz.patientmanagement.domain.Prescription;
import org.xyz.patientmanagement.domain.YesNoMap;
import org.xyz.patientmanagement.model.Medicine;
import org.xyz.patientmanagement.model.MedicineForm;
import org.xyz.patientmanagement.model.Test;
import org.xyz.patientmanagement.service.DoctorService;
import org.xyz.patientmanagement.service.PatientService;
import org.xyz.patientmanagement.service.PrescriptionService;

import java.util.Base64;
import java.util.List;

import static java.util.Arrays.asList;
import static org.xyz.patientmanagement.util.Constants.*;
import static org.xyz.patientmanagement.util.Util.*;

/**
 * @author inanmashrur
 * @since 6/9/2023
 */
@Component
public class PrescriptionHelper {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    public Prescription getCommandObject() {
        long prescriptionId = ServletRequestUtils.getLongParameter(getRequestObject(), PRESCRIPTION_ID, DEFAULT);

        if (isValidIdentifier(prescriptionId)) {
            return preparePrescriptionForView(prescriptionService.findById(prescriptionId));
        }

        long patientId = ServletRequestUtils.getLongParameter(getRequestObject(), PATIENT_ID, DEFAULT);
        long doctorId = ServletRequestUtils.getLongParameter(getRequestObject(), DOCTOR_ID, DEFAULT);

        Patient patient = patientService.findActiveById(patientId);
        Doctor doctor = doctorService.findActiveById(doctorId);

        if (isNonNull(patient, doctor)) {
            return new Prescription(patient, doctor);
        }

        throw new IllegalArgumentException();
    }

    public void setUpReferenceData(ModelMap modelMap) {
        modelMap.put("medicineFormList", asList(MedicineForm.values()));
        modelMap.put("yesNoMap", asList(YesNoMap.values()));
    }

    public void performRequiredConversionBeforeSave(Prescription prescription) {
        prescription.setMedicinesInJson(mapToJson(prescription.getMedicineList()));
        prescription.setTestsInJson(mapToJson(prescription.getTestList()));
    }

    public Prescription preparePrescriptionForView(Prescription prescription) {
        if (isNonNull(prescription)) {
            prescription.setMedicineList(convertToMedicineList(prescription.getMedicinesInJson()));
            prescription.setTestList(convertToTestList(prescription.getTestsInJson()));
            prescription.getAttachments()
                    .forEach(attachment ->
                            attachment.setDataInBase64(Base64.getEncoder().encodeToString(attachment.getData())));
        }

        return prescription;
    }

    public String mapToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(object);

        } catch (Exception e) {
            return null;
        }
    }

    public List<Medicine> convertToMedicineList(String listInJson) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(listInJson, new TypeReference<List<Medicine>>() {});

        } catch (Exception e) {
            return null;
        }
    }

    public List<Test> convertToTestList(String listInJson) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(listInJson, new TypeReference<List<Test>>() {});

        } catch (Exception e) {
            return null;
        }
    }
}
