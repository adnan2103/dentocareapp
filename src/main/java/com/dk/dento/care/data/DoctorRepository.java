package com.dk.dento.care.data;

import org.springframework.data.repository.CrudRepository;

/**
 * Allows managing {@link Doctor} instances.
 *
 *
 */
public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    Doctor findByEmail(String email);
}
