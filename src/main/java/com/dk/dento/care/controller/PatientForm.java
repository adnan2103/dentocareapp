package com.dk.dento.care.controller;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class PatientForm {

    @NotEmpty(message = "Patient is required.")
    private String message;

    @NotEmpty(message = "Summary is required.")
    private String summary;

    @NotEmpty(message = "Email is required.")
    @Email
    private String toEmail;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }
}
