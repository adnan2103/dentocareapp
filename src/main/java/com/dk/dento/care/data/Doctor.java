package com.dk.dento.care.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Represents a user in our system.
 *
 * <p>
 * In a real system use {@link PasswordEncoder} to ensure the password is secured
 * properly. This demonstration does not address this due to time restrictions.
 * </p>
 */
@Entity
public class Doctor implements Serializable {

    @Id
    @SequenceGenerator(name = "doctor_id_seq", sequenceName = "doctor_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_id_seq")
    private Long id;

    @NotEmpty(message = "First name is required.")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last name is required.")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Please provide a valid email address.")
    @NotEmpty(message = "Email is required.")
    @Column(unique=true, nullable = false)
    private String email;

    @NotEmpty(message = "Password is required.")
    private String password;

    public Doctor() {}

    public Doctor(Doctor doctor) {
        this.id = doctor.id;
        this.firstName = doctor.firstName;
        this.lastName = doctor.lastName;
        this.email = doctor.email;
        this.password = doctor.password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private static final long serialVersionUID = 2738859149330833739L;
}