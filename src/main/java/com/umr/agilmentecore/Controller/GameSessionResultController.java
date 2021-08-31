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

import com.umr.agilmentecore.Class.IntermediateClasses.EncuentraAlNuevoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.HayUnoRepetidoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.PatientResultsView;
import com.umr.agilmentecore.Class.IntermediateClasses.ResultsListView;
import com.umr.agilmentecore.Services.GameSessionResultService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/results")
public class GameSessionResultController {
	@Autowired
	private GameSessionResultService service;
	
	/**
	 * Obtiene la lista de resultados completa.
	 * Devuelve una lista de ResultsData
	 */
	@GetMapping
	public Page<ResultsListView> getAllResultsOrdered(Pageable page) {
		return this.service.getAllResultsOrdered(page);
	}
	
	/**
	 * Obtiene un resultado de HayUnoRepetido a partir del id.
	 * @param id Id del resultado.
	 * @return El resultado buscado.
	 */
	@GetMapping(value = "/encuentra-al-repetido/{id}")
	public Optional<HayUnoRepetidoResultDetailView> getOne(@PathVariable(name = "id")Long id) {
		return this.service.getOneHayUnoRepetido(id);
	}
	
	/**
	 * Recibe un resultado de Encuentra al Nuevo y lo guarda.
	 * @param result Un resultado con los datos de EncuentraAlNuevoResultDetailView.
	 */
	@PostMapping(path = "/encuentra-al-nuevo")
	public void save(@RequestBody EncuentraAlNuevoResultDetailView result) {
		service.saveEncuentraAlNuevo(result);
	}
	
	/**
	 * Recibe un resultado de Encuentra al Repetido y lo guarda.
	 * @param result Un resultado con los datos de EncuentraAlNuevoResultDetailView.
	 */
	@PostMapping(path = "/encuentra-al-repetido")
	public void save(@RequestBody HayUnoRepetidoResultDetailView result) {
		service.saveHayUnoRepetido(result);
	}

	/**
	 * Obtiene una lista de todos los resultados
	 * a partir del id de un paciente.
	 * @param id ID del paciente.
	 * @return Devuelve la vista de los resultados.
	 */
	@GetMapping(value = "/by-patient/{id}")
	public  PatientResultsView getAllResultsByPatient(@PathVariable(name = "id")Long id) {
		return this.service.getAllResultsByPatient(id);
	}
}
