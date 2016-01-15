package com.dk.dento.care.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PostAuthorize;

/**
 * Manages {@link Patient} instances
 *
 *
 */
public interface PatientRepository extends CrudRepository<Patient, Long> {

    @Query("select p from Patient p where p.to.id = ?#{principal.id}")
    Iterable<Patient> findAllToCurrentUser();

    @PostAuthorize("hasPermission(returnObject,'read')")
    Patient findOne(Long id);
}
