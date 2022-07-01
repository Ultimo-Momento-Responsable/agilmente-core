package com.umr.agilmentecore.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	 * Obtiene todos los resultados de los Dominios Cognitivos.
	 * @return Una lista de resultados.
	 */
	@GetMapping
	public List<CognitiveDomain> getAll() {
		return service.getAll();
	}
	
}
