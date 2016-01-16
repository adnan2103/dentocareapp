package com.dk.dento.care.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.dk.dento.care.data.*;
import com.dk.dento.care.data.Patient;

/**
 * Controller for managing {@link Patient} instances.
 *
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

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Doctor currentDoctor = (Doctor) authentication.getPrincipal();
        // @TODO currentDoctor object shuld be loaded with all patients.
        Iterable<Patient> patients = doctorRepository.findOne(currentDoctor.getDoctorId()).getPatients();
        model.addAttribute("patients", patients);
        return "patient/dashboard";
    }

    @RequestMapping(value = "{id}", method=RequestMethod.GET)
    public String view(@PathVariable Long id, Model model) {
        Patient patient = patientRepository.findOne(id);
        model.addAttribute("patient",patient);
        return "patient/detail";
    }

    @RequestMapping(value = "{id}", method=RequestMethod.POST)
    public String delete(@PathVariable("id") Patient patient, RedirectAttributes redirect) {
        patientRepository.delete(patient);
        redirect.addFlashAttribute("globalMessage", "Patient removed successfully");
        return "redirect:/";
    }

    @RequestMapping(params="add", method=RequestMethod.GET)
    public String createForm(@ModelAttribute PatientForm patientForm) {
        return "patient/add";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String create(@Valid PatientForm patientForm, RedirectAttributes redirect) {


        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Doctor currentDoctor = (Doctor) authentication.getPrincipal();

        Patient patient = new Patient();
        patient.setDoctor(currentDoctor);
        patient.setFirstName(patientForm.getFirstName());
        patient.setLastName(patientForm.getLastName());

        patientRepository.save(patient);


        
        redirect.addFlashAttribute("globalMessage", "Patient added successfully");
        return "redirect:/";
    }
}
