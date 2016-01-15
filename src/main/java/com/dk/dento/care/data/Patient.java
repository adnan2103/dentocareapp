package com.dk.dento.care.data;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * A Patient is an entity that can be sent to a {@link Doctor}.
 *
 */
@Entity
public class Patient {
    @Id
    @SequenceGenerator(name = "patient_id_seq", sequenceName = "patient_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "patient_id_seq")
    private Long id;

    @NotEmpty(message = "Patient is required.")
    private String message;

    @NotEmpty(message = "Summary is required.")
    private String summary;

    private Calendar created = Calendar.getInstance();

    @OneToOne(fetch= FetchType.EAGER,cascade= CascadeType.ALL)
    @NotNull
    private Doctor to;

    @OneToOne(fetch= FetchType.EAGER,cascade= CascadeType.ALL)
    @NotNull
    private Doctor from;
    
    public Doctor getFrom() {
		return from;
	}

	public void setFrom(Doctor from) {
		this.from = from;
	}

	public Doctor getTo() {
        return to;
    }

    public void setTo(Doctor to) {
        this.to = to;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

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

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", summary='" + summary + '\'' +
                ", created=" + created +
                ", to=" + to +
                ", from=" + from +
                '}';
    }
}