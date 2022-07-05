package com.umr.agilmentecore.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.umr.agilmentecore.Class.Planning;
import com.umr.agilmentecore.Class.PlanningState;
import com.umr.agilmentecore.Class.IntermediateClasses.PlanningData;
import com.umr.agilmentecore.Class.IntermediateClasses.PlanningOverview;
import com.umr.agilmentecore.Class.IntermediateClasses.PlanningWithSessions;
import com.umr.agilmentecore.Services.PlanningService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/planning")
public class PlanningController {
	@Autowired
	private PlanningService service;

	/**
	 * Obtiene todas las planificaciones paginadas.
	 * 
	 * @return Página de planificaciones.
	 */
	@GetMapping
	public List<Planning> getAll() {
		return service.getAll();
	}

	/**
	 * Obtiene las plannings vigentes y pendientes con el filtro
	 * 
	 * @param search Texto a buscar en la planning.
	 * @param states Estados que se están filtrando.
	 * @param patientId Id del paciente.
	 * @return Listado de plannings con filtro.
	 */
	@GetMapping(value = "/overview")
	public List<PlanningOverview> getAllFiltered(@RequestParam(required = false) Optional<String> search, @RequestParam(required = false) Optional<String[]> states, @RequestParam(required = false) Optional<Long> patientId) {
		if (search.isEmpty() && states.isEmpty() && patientId.isEmpty()) {
			return service.getPlanningOverview();
		}
		
		String searchValue = search.orElse("");
		Long patientIdValue = patientId.orElse(null);
		ArrayList<String> statesValue = new ArrayList<String>();
		
		if (states.isPresent()) {
			Collections.addAll(statesValue, states.get());
		}
		
		return service.getAllFiltered(searchValue, statesValue, patientIdValue);
	}

	/**
	 * Obtiene una planificacion.
	 * 
	 * @param id Numero id de la planificación
	 * @return Única planificación.
	 */
	@GetMapping(value = "/{id}")
	public PlanningData getOne(@PathVariable(name = "id") Long id) {
		return service.getOnePlanningData(id);
	}

	/**
	 * Crea una planificación.
	 * 
	 * @param planning Datos de la planificación.
	 * @return Una instancia de planificación.
	 */
	@PostMapping
	public Planning save(@RequestBody PlanningData planning) throws Exception {
		return this.service.save(planning);
	}

	/**
	 * Edita una planificación vigente.
	 * @param planning. planificacion con nuevos datos
	 * @param id. id de la planificación a editar
	 * @return Planificación editada.
	 * @throws Exception
	 */
	@PutMapping(value="/edit/{id}")
	public Planning editActivePlanning(@PathVariable(name = "id") Long id, @RequestBody PlanningData planning) throws Exception {
		return this.service.edit(planning,id);
	}

	/**
	 * Obtiene una Planificación.
	 * 
	 * @param Long el patientId del paciente específico.
	 * @return Optional una planificación o nada.
	 */
	@GetMapping(params = {"patientId"})
	public List<Planning> getCurrentPlanningsFromPatient(Long patientId) {
		return service.getCurrentPlanningsFromPatient(patientId);
	}

	/**
	 * Obtiene todas las Planificaciones cargadas al paciente.
	 * 
	 * @param Long el id del paciente específico.
	 * @return Todas las planificaciones del paciente.
	 */
	@GetMapping(value = "/mobile_patient/{id}")
	public List<PlanningWithSessions> getCurrentPlanningsFromPatientForMobile(@PathVariable(name = "id") Long id) {
		return service.getCurrentPlanningsFromPatientForMobile(id);
	}

	/**
	 * Obtiene una lista con todos los posibles estados de planificación
	 * 
	 * @return lista de PlanningState
	 */
	@GetMapping(value = "/states")
	public List<PlanningState> getPlanningStates() {
		return service.getPlanningStates();
	}

	/**
	 * Cancela una planificación
	 * 
	 * @param Long el id de la planificación.
	 */
	@RequestMapping(value = "/cancel_planning/{id}", method = RequestMethod.PUT)
	public void cancelPlanning(@PathVariable(name = "id") Long id) {
		boolean isCancellable = service.cancel(id);
		if (isCancellable) {
			throw new ResponseStatusException(HttpStatus.ACCEPTED, "Planning canceled");
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Planning is already canceled or finished");
		}
	}
}
