package com.umr.agilmentecore.Persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.Patient;

import org.springframework.data.jpa.repository.Query;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	Optional<Patient> findById(Long id);
	Optional<Patient> findByLoginCode(String loginCode);
	ArrayList<Patient> findAllByIsEnabledTrue();
	
	@Query("select p from Patient p where TRANSLATE(LOWER(p.firstName || ' ' || p.lastName),'áéíóú', 'aeiou') like %?1% AND p.isEnabled = TRUE")
	List<Patient> findByFullNameContainingIgnoreCaseActive(String fullName);
	
	@Query("select p from Patient p where TRANSLATE(LOWER(p.firstName || ' ' || p.lastName),'áéíóú', 'aeiou') like %?1%")
	List<Patient> findAllByFullNameContainingIgnoreCaseActive(String fullName);
	
	@Query("select p from Patient p where TRANSLATE(LOWER(p.firstName || ' ' || p.lastName),'áéíóú', 'aeiou') like %?1%")
	List<Patient> findByFullNameContainingIgnoreCase(String fullName);
	
}