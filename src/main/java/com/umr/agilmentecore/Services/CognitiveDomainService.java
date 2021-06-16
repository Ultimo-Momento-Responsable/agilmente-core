package com.umr.agilmentecore.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.CognitiveDomain;
import com.umr.agilmentecore.Persistence.CognitiveDomainRepository;

@Service
public class CognitiveDomainService {

	@Autowired
	private CognitiveDomainRepository repository;
	
	public Page<CognitiveDomain> getAll(Pageable page) {
		return repository.findAll(page);
	}
	
	public CognitiveDomain saveGame(CognitiveDomain cd) {
		
		return repository.save(cd);
	}
}
