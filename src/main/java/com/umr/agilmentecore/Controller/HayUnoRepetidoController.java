package com.umr.agilmentecore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umr.agilmentecore.Class.HayUnoRepetido;
import com.umr.agilmentecore.Services.HayUnoRepetidoService;

@RestController
@RequestMapping("/hay-uno-repetido")
public class HayUnoRepetidoController {

	@Autowired
	private HayUnoRepetidoService service;
	
	// Obtiene todos los resultados de Hay uno Repetido
	@GetMapping
	public Page<HayUnoRepetido> getAll(Pageable page) {
		return service.getAll(page);
	}
	
	// Guarda un resultado de Hay uno Repetido
	@PostMapping
	public HayUnoRepetido saveGame(@RequestBody HayUnoRepetido g) {
		
		return service.saveGame(g);
	}

}
