package com.umr.agilmentecore.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.Professional;
import com.umr.agilmentecore.Persistence.ProfessionalRepository;

@Service
public class ProfessionalService {
	@Autowired
	private ProfessionalRepository repository;
	
	public Optional<Professional> getOne(Long id) {
		return this.repository.findById(id);
	}
}
