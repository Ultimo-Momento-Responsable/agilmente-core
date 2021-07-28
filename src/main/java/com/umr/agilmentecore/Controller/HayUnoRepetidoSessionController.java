package com.umr.agilmentecore.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umr.agilmentecore.Class.HayUnoRepetidoSession;
import com.umr.agilmentecore.Services.HayUnoRepetidoSessionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/hay-uno-repetido")
public class HayUnoRepetidoSessionController {

	@Autowired
	private HayUnoRepetidoSessionService service;
	
	/**
	 *  Obtiene todos los resultados de HayUnoRepetido
	 * @param page Contiene las opciones de paginación
	 * @return Una página de resultados
	 */
	@GetMapping
	public Page<HayUnoRepetidoSession> getAll(Pageable page) {
		return service.getAll(page);
	}
	
	/**
	 *  Obtiene un resultado de HayUnoRepetido
	 * @param Long el id del juego específico
	 * @return Optional un resultado de un juego o nada.
	 */
	@GetMapping(value = "/{id}")
	public Optional<HayUnoRepetidoSession> getOne(@PathVariable(name = "id") Long id) {
		return service.getOne(id);
	}
	
	/**
	 * Guarda un resultado de Hay uno Repetido
	 * @param g Un resultado de HayUnoRepetido
	 * @return el resultado del juego guardado
	 */
	@PostMapping
	public HayUnoRepetidoSession save(@RequestBody HayUnoRepetidoSession g) {
		
		return service.save(g);
	}

}
