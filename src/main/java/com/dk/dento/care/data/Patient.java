package com.dk.dento.care.data;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.print.Doc;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * A Patient is an entity that can be sent to a {@link Doctor}.
 *
 */
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @SequenceGenerator(name = "patient_id_seq", sequenceName = "patient_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "patient_id_seq")
    @Column(name = "patient_id")
    private Long patientId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor", nullable = false, referencedColumnName = "doctor_id")
    private Doctor doctor;

    @NotEmpty(message = "Patient First Name is required.")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Patient Last Name required.")
    @Column(name = "last_name")
    private String lastName;


    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patinetId) {
        this.patientId = patinetId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}