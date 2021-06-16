package com.umr.agilmentecore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umr.agilmentecore.Class.CognitiveDomain;
import com.umr.agilmentecore.Services.CognitiveDomainService;

@RestController
@RequestMapping("/cognitive-domain")
public class CognitiveDomainController {

	@Autowired
	private CognitiveDomainService service;
	
	// Obtiene todos los dominios cognitivos
	@GetMapping
	public Page<CognitiveDomain> getAll(Pageable page) {
		return service.getAll(page);
	}
	
	// Guarda un dominio cognitivo
	@PostMapping
	public CognitiveDomain saveGame(@RequestBody CognitiveDomain cd) {
		
		return service.saveGame(cd);
	}
}
