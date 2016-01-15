package com.dk.dento.care.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PostAuthorize;


/**
 * Manages {@link Patient} instances
 *
 *
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {
    
    @PostAuthorize("hasPermission(returnObject,'read')")
    Patient findOne(Long id);
}
