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
	
	/**
	 *  Obtiene todos los resultados de los Dominios Cognitivos
	 * @param page Contiene las opciones de paginación
	 * @return Una página de resultados
	 */
	public Page<CognitiveDomain> getAll(Pageable page) {
		return repository.findAll(page);
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
