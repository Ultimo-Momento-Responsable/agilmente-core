package com.umr.agilmentecore.Persistence;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.Patient;

import org.springframework.data.jpa.repository.Query;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	Page<Patient> findAll(Pageable page);
	Page<Patient> findAllByIsEnabledTrue(Pageable page);
	Optional<Patient> findById(Long id);
	Optional<Patient> findByLoginCode(String loginCode);
	ArrayList<Patient> findAll();
	ArrayList<Patient> findAllByIsEnabledTrue();
	
	@Query("select p from Patient p where TRANSLATE(LOWER(p.firstName || ' ' || p.lastName),'áéíóú', 'aeiou') like %?1% AND p.isEnabled = TRUE")
	Page<Patient> findByFullNameContainingIgnoreCaseActive(String fullName, Pageable page);
	
	@Query("select p from Patient p where TRANSLATE(LOWER(p.firstName || ' ' || p.lastName),'áéíóú', 'aeiou') like %?1%")
	Page<Patient> findByFullNameContainingIgnoreCase(String fullName, Pageable page);
	
}