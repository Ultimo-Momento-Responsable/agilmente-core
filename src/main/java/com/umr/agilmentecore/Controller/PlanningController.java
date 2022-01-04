package com.umr.agilmentecore.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.umr.agilmentecore.Class.Planning;
import com.umr.agilmentecore.Class.IntermediateClasses.PlanningData;
import com.umr.agilmentecore.Class.IntermediateClasses.PlanningList;
import com.umr.agilmentecore.Class.IntermediateClasses.PlanningOverview;
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
	 * Obtiene todas las planificaciones vigentes y pendientes paginadas sin juegos.
	 * @return Página de planificaciones vigentes y pendientes sin juegos.
	 */
	@GetMapping(value = "/planningOverview")
	public Page<PlanningOverview> getOverviews() {
		return service.getPlanningOverview();
	}
	
	/**
	 * Obtiene las plannings vigentes y pendientes con el filtro
	 * @param search filtro
	 * @return Listado de plannings con filtro
	 */
	@GetMapping(value = "/filter/{search}")
	public List<Planning> getPlanningsFiltered(@PathVariable(name = "search") String search) {
		return service.getPlanningsFiltered(search);
	}
	
	/**
	 * Obtiene una planificacion.
	 * @param id Numero id de la planificación
	 * @return Única planificación.
	 */
	@GetMapping(value = "/{id}")
	public PlanningData getOne(@PathVariable(name = "id") Long id) {
		return service.getOne(id);
	}
	
	/**
	 * Crea una planificación.
	 * @param planning Datos de la planificación.
	 * @return Una instancia de planificación.
	 */
	@PostMapping
	public Planning save(@RequestBody PlanningData planning) throws Exception {
		return this.service.save(planning);
	}
	/**
	 * Obtiene una Planificación.
	 * @param Long el id del paciente específico.
	 * @return Optional una planificación o nada.
	 */
	@GetMapping(value = "/patient_{id}")
	public List<Planning> getCurrentPlanningsFromPatient(@PathVariable(name = "id") Long id) {
		return service.getCurrentPlanningsFromPatient(id);
	}
	
	/**
	 * Obtiene una Planificación.
	 * @param Long el id del paciente específico.
	 * @return Optional una planificación o nada.
	 */
	@GetMapping(value = "/mobile_patient_{id}")
	public PlanningList getCurrentPlanningsFromPatientForMobile(@PathVariable(name = "id") Long id) {
		return service.getCurrentPlanningsFromPatientForMobile(id);
	}
	
	/**
	 * Cancela una planificación
	 * @param Long el id de la planificación.
	 */
	@RequestMapping(value = "/cancel_planning/{id}", method = RequestMethod.PUT)
	public void cancelPlanning(@PathVariable(name = "id") Long id) {
		boolean isCancellable = service.cancel(id);
		if (isCancellable) {
			throw new ResponseStatusException(
			  HttpStatus.ACCEPTED, "Planning canceled"
			);
		} else {
			throw new ResponseStatusException(
			  HttpStatus.BAD_REQUEST, "Planning is already canceled or finished"
			);
		}
	}
}
