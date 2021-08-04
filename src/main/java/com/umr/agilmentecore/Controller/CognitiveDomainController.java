package com.umr.agilmentecore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umr.agilmentecore.Class.CognitiveDomain;
import com.umr.agilmentecore.Services.CognitiveDomainService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cognitive-domain")
public class CognitiveDomainController {

	@Autowired
	private CognitiveDomainService service;
	
	/**
	 *  Obtiene todos los resultados de los Dominios Cognitivos
	 * @param page Contiene las opciones de paginación
	 * @return Una página de resultados
	 */
	@GetMapping
	public Page<CognitiveDomain> getAll(Pageable page) {
		return service.getAll(page);
	}
	
	/**
	 * Guarda un Dominio Cognitivo
	 * @param cd Un Dominio Cognitivo
	 * @return el Dominio Cognitivo guardado
	 */
	@PostMapping
	public CognitiveDomain save(@RequestBody CognitiveDomain cd) {
		return service.save(cd);
	}
}
