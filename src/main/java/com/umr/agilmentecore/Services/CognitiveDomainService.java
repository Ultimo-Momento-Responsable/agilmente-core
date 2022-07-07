package com.umr.agilmentecore.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.CognitiveDomain;
import com.umr.agilmentecore.Persistence.CognitiveDomainRepository;

@Service
public class CognitiveDomainService {

	@Autowired
	private CognitiveDomainRepository repository;
	
	/**
	 *  Obtiene todos los resultados de los Dominios Cognitivos
	 * @return Una lista de resultados
	 */
	public List<CognitiveDomain> getAll() {
		return repository.findAll();
	}
	
	/**
	 * Guarda un Dominio Cognitivo
	 * @param cd Un Dominio Cognitivo
	 * @return el Dominio Cognitivo guardado
	 */
	public CognitiveDomain save(CognitiveDomain cd) {
		return repository.save(cd);
	}
}
