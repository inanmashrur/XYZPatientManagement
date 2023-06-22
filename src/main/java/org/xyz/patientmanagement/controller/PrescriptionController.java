package org.xyz.patientmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xyz.patientmanagement.domain.*;
import org.xyz.patientmanagement.editor.MedicineEditor;
import org.xyz.patientmanagement.editor.TestEditor;
import org.xyz.patientmanagement.helper.PrescriptionHelper;
import org.xyz.patientmanagement.model.Medicine;
import org.xyz.patientmanagement.model.MedicineForm;
import org.xyz.patientmanagement.model.Test;
import org.xyz.patientmanagement.service.DoctorService;
import org.xyz.patientmanagement.service.PatientService;
import org.xyz.patientmanagement.service.PrescriptionService;
import org.xyz.patientmanagement.util.Util;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Objects.isNull;
import static org.xyz.patientmanagement.util.Constants.*;
import static org.xyz.patientmanagement.util.Util.*;

@Controller
@RequestMapping("/prescription")
@SessionAttributes(COMMAND_PRESCRIPTION)
public class PrescriptionController {

    private static final String FORM_PAGE = "prescription/form";

    private static final String LIST_PAGE = "prescription/list";

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private PrescriptionHelper prescriptionHelper;

    @Autowired
    private MedicineEditor medicineEditor;

    @Autowired
    private TestEditor testEditor;

    @Autowired
    private MessageSourceAccessor msa;

    @InitBinder(COMMAND_PRESCRIPTION)
    public void initBinderForDoctor(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        dataBinder.registerCustomEditor(Medicine.class, medicineEditor);
        dataBinder.registerCustomEditor(Test.class, testEditor);
    }

    @GetMapping("/form")
    public String show(@RequestParam(required = false) Long prescriptionId,
                       @RequestParam(required = false) Long patientId,
                       @RequestParam(required = false) Long doctorId,
                       ModelMap modelMap) {

        if (isNull(prescriptionId) && !isNonNull(patientId, doctorId)) {
            return "error";
        }

        modelMap.put(COMMAND_PRESCRIPTION, prescriptionHelper.getCommandObject());
        prescriptionHelper.setUpReferenceData(modelMap);

        return FORM_PAGE;
    }

    @PostMapping(value = "/form", params = "_action_upload")
    public String addAttachment(@ModelAttribute(COMMAND_PRESCRIPTION) Prescription prescription,
                                       @RequestParam MultipartFile file) throws IOException {

        Attachment attachment = new Attachment(file.getOriginalFilename(), file.getSize(), file.getContentType(), file.getBytes());
        attachment.setDataInBase64(Base64.getEncoder().encodeToString(attachment.getData()));

        List<Attachment> attachments = prescription.getAttachments();
        attachments.add(attachment);

        prescription.setAttachments(attachments);

        return FORM_PAGE;
    }

    @PostMapping(value = "/form", params = "_action_remove")
    public String removeAttachment(@ModelAttribute(COMMAND_PRESCRIPTION) Prescription prescription) {
        return FORM_PAGE;
    }

    @PostMapping(value = "/form", params = "_action_save_or_update")
    public String save(@Valid @ModelAttribute(COMMAND_PRESCRIPTION) Prescription prescription,
                       BindingResult bindingResult,
                       SessionStatus sessionStatus,
                       RedirectAttributes redirectAttributes,
                       ModelMap modelMap) {

        System.out.println(prescription);

        if (bindingResult.hasErrors()) {
            prescriptionHelper.setUpReferenceData(modelMap);
            return FORM_PAGE;
        }

        prescriptionHelper.performRequiredConversionBeforeSave(prescription);
        prescriptionService.saveOrUpdate(prescription);
        sessionStatus.setComplete();

        return redirectToMsgPage(redirectAttributes, msa, SUCCESS, "save.success", "Prescription");
    }
}
