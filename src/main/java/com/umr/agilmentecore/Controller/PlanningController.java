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

import com.umr.agilmentecore.Class.Planning;
import com.umr.agilmentecore.Class.IntermediateClasses.PlanningData;
import com.umr.agilmentecore.Services.PlanningService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/planning")
public class PlanningController {
	@Autowired
	private PlanningService service;
	
	/**
	 * Obtiene todas las planificaciones paginadas.
	 * @param page Opciones de paginación.
	 * @return Página de planificaciones.
	 */
	@GetMapping
	public Page<Planning> getAll(Pageable page) {
		return service.getAll(page);
	}
	
	/**
	 * Crea una planificación.
	 * @param planning Datos de la planificación.
	 * @return Una instancia de planificación.
	 */
	@PostMapping
	public Planning save(@RequestBody PlanningData planning) {
		return this.service.save(planning);
	}
}
