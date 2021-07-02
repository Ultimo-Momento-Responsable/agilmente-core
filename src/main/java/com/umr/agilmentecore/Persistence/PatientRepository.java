package com.umr.agilmentecore.Persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	Page<Patient> findAll(Pageable page);
	Optional<Patient> findById(Long id);
}