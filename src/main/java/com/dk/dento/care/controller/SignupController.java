package com.dk.dento.care.controller;

import java.util.List;

import javax.validation.Valid;

import com.dk.dento.care.data.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.dk.dento.care.data.DoctorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Allows users to sign up.
 *
 *
 */
@Controller
@RequestMapping("/signup")
public class SignupController {
    private DoctorRepository doctorRepository;

    @Autowired
    public SignupController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String signupForm(@ModelAttribute Doctor doctor) {
        return "doctor/signup";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String signup(@Valid Doctor doctor, BindingResult result,
            RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "doctor/signup";
        }
        doctor = doctorRepository.save(doctor);
        redirect.addFlashAttribute("globalMessage", "Successfully signed up");

        List<GrantedAuthority> authorities =
            AuthorityUtils.createAuthorityList("ROLE_USER");
        UserDetails userDetails = new org.springframework.security.core.userdetails
            .User(doctor.getEmail(), doctor.getPassword(), authorities);
        Authentication auth =
            new UsernamePasswordAuthenticationToken(userDetails, doctor.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "redirect:/";
    }
}
