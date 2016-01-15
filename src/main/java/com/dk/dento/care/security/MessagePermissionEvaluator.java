package com.dk.dento.care.security;

import java.io.Serializable;

import com.dk.dento.care.data.Doctor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import com.dk.dento.care.data.Patient;
import org.springframework.stereotype.Component;


@Component
public class MessagePermissionEvaluator implements PermissionEvaluator {
	/* (non-Javadoc)
	 * @see org.springframework.security.access.PermissionEvaluator#hasPermission(org.springframework.security.core.Authentication, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {
		if(authentication == null) {
			return false;
		}
		Patient patient = (Patient) targetDomainObject;
		if(patient == null) {
			return true;
		}
		Doctor currentDoctor = (Doctor) authentication.getPrincipal();
		return currentDoctor.getDoctorId().equals(patient.getDoctor().getDoctorId());
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.PermissionEvaluator#hasPermission(org.springframework.security.core.Authentication, java.io.Serializable, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		throw new UnsupportedOperationException();
	}

}
