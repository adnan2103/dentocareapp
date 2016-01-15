package com.dk.dento.care.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.dk.dento.care.data.*;
import com.dk.dento.care.data.Patient;
import com.dk.dento.care.security.CurrentUser;

/**
 * Controller for managing {@link Patient} instances.
 *
 * @author Rob Winch
 *
 */
@Controller
@RequestMapping("/")
public class PatientController {
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;

    @Autowired
    public PatientController(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @RequestMapping(method=RequestMethod.GET)
    public String list(Model model) {
        Iterable<Patient> patients = patientRepository.findAllToCurrentUser();
        model.addAttribute("patients", patients);
        return "patient/dashboard";
    }

    @RequestMapping(value = "{id}", method=RequestMethod.GET)
    public String view(@PathVariable Long id, Model model) {
        Patient patient = patientRepository.findOne(id);
        System.out.println("Adnan : "+patient);
        model.addAttribute("patient",patient);
        return "patient/detail";
    }

    @RequestMapping(value = "{id}", method=RequestMethod.DELETE)
    public String delete(@PathVariable("id") Patient patient, RedirectAttributes redirect) {
        patientRepository.delete(patient);
        redirect.addFlashAttribute("globalMessage", "Patient removed successfully");
        return "redirect:/";
    }

    @RequestMapping(params="form", method=RequestMethod.GET)
    public String createForm(@ModelAttribute PatientForm patientForm) {
        return "patient/add";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String create(@CurrentUser Doctor currentDoctor, @Valid PatientForm patientForm, BindingResult result, RedirectAttributes redirect) {
        Doctor to = doctorRepository.findByEmail(patientForm.getToEmail());
        if(to == null) {
            result.rejectValue("toEmail","toEmail", "Doctor not found");
        }
        if(result.hasErrors()) {
            return "patient/add";
        }

        Patient patient = new Patient();
        patient.setSummary(patientForm.getSummary());
        patient.setMessage(patientForm.getMessage());
        patient.setTo(to);
        patient.setFrom(currentDoctor);

        patientRepository.save(patient);

        
        redirect.addFlashAttribute("globalMessage", "Patient added successfully");
        return "redirect:/";
    }
}
