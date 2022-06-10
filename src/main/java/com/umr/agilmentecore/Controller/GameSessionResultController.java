package com.umr.agilmentecore.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.umr.agilmentecore.Class.EncuentraAlNuevoResult;
import com.umr.agilmentecore.Class.HayUnoRepetidoResult;
import com.umr.agilmentecore.Class.MemorillaResult;
import com.umr.agilmentecore.Class.IntermediateClasses.EncuentraAlNuevoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.HayUnoRepetidoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.MemorillaResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.PatientResultsView;
import com.umr.agilmentecore.Class.IntermediateClasses.ResultListHistory;
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
	public Page<ResultsListView> getAllResultsOrdered() {
		return this.service.getAllResultsOrdered();
	}
	
	/**
	 * Obtiene la lista de resultados de una planning completa.
	 * Devuelve una lista de ResultsData
	 */
	@GetMapping(value = "/planning/{id}")
	public Page<ResultsListView> getAllPlanningResultsOrdered(@PathVariable(name = "id")Long planningId) {
		return this.service.getAllPlanningResultsOrdered(planningId);
	}
	
	/**
	 * Obtiene un resultado de HayUnoRepetido a partir del id.
	 * @param id Id del resultado.
	 * @return El resultado buscado.
	 */
	@GetMapping(value = "/encuentra-al-repetido/{id}")
	public HayUnoRepetidoResultDetailView getOneHUR(@PathVariable(name = "id")Long id) {
		if (this.service.getOneHayUnoRepetido(id) == null) {
			throw new ResponseStatusException(
			  HttpStatus.NOT_FOUND, "Result not found"
			);
		}
		return this.service.getOneHayUnoRepetido(id);
	}
	
	/**
	 * Obtiene un resultado de Memorilla a partir del id.
	 * @param id Id del resultado.
	 * @return El resultado buscado.
	 */
	@GetMapping(value = "/memorilla/{id}")
	public MemorillaResultDetailView getOneM(@PathVariable(name = "id")Long id) {
		if (this.service.getOneMemorilla(id) == null) {
			throw new ResponseStatusException(
			  HttpStatus.NOT_FOUND, "Result not found"
			);
		}
		return this.service.getOneMemorilla(id);
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
	 * Recibe un resultado de Memorilla y lo guarda.
	 * @param result Un resultado con los datos de MemorillaResultDetailView.
	 */
	@PostMapping(path = "/memorilla")
	public void save(@RequestBody MemorillaResultDetailView result) {
		service.saveMemorilla(result);
	}

	/**
	 * Obtiene una lista de todos los resultados
	 * a partir del id de un paciente.
	 * @param id ID del paciente.
	 * @return Devuelve la vista de los resultados.
	 */
	@GetMapping(value = "/by-patient/{id}")
	public  PatientResultsView getAllResultsByPatient(@PathVariable(name = "id")Long id) {
		if (this.service.getAllResultsByPatient(id) == null) {
			throw new ResponseStatusException(
			  HttpStatus.NOT_FOUND, "Patient not found"
			);
		}
		return this.service.getAllResultsByPatient(id);
	}
	
	/**
	 * Obtiene una lista de los resultados de un paciente ordenados por fecha
	 * El formato del resultado es del formato ResultListHistory
	 * @param id
	 * @return Devuelve una lista con los resultados.
	 */
	@GetMapping(value = "/by-patient-ordered/{id}")
	public  List<ResultListHistory> getAllResultsByPatientOrdered(@PathVariable(name = "id")Long id) {
		if (this.service.getAllResultsByPatient(id) == null) {
			throw new ResponseStatusException(
			  HttpStatus.NOT_FOUND, "Patient not found"
			);
		}
		return this.service.getAllResultsByPatientOrdered(id);
	}
	
	/**
	 * Obtiene un resultado de Encuentra al Nuevo a partir del id.
	 * @param id Id del resultado.
	 * @return El resultado buscado.
	 */
	@GetMapping(value = "/encuentra-al-nuevo/{id}")
	public EncuentraAlNuevoResultDetailView getOneEAN(@PathVariable(name = "id")Long id) {
		if (this.service.getOneEncuentraAlNuevo(id) == null) {
			throw new ResponseStatusException(
			  HttpStatus.NOT_FOUND, "Result not found"
			);
		}
		return this.service.getOneEncuentraAlNuevo(id);
	}
	
	/**
	 * Busca todos los resultados de HayUnoRepetidoResult a partir
	 * del id de la sesión.
	 * @param id ID de la sesión..
	 * @return Lista de resultados.
	 */
	@GetMapping(value = "/by-game-session/encuentra-al-repetido/{id}")
	public List<HayUnoRepetidoResult> getAllHayUnoRepetidoResultsBySessionId(@PathVariable(name = "id")Long id) {
		return this.service.getAllHayUnoRepetidoResultsBySessionId(id);
	}
	
	/**
	 * Busca todos los resultados de EncuentraAlNuevoResult a partir
	 * del id de la sesión.
	 * @param id ID de la sesión..
	 * @return Lista de resultados.
	 */
	@GetMapping(value = "/by-game-session/encuentra-al-nuevo/{id}")
	public List<EncuentraAlNuevoResult> getAllEncuentraAlNuevoResultsBySessionId(@PathVariable(name = "id")Long id) {
		return this.service.getAllEncuentraAlNuevoResultsBySessionId(id);
	}
	
	/**
	 * Busca todos los resultados de MemorillaResult a partir
	 * del id de la sesión.
	 * @param id ID de la sesión..
	 * @return Lista de resultados.
	 */
	@GetMapping(value = "/by-game-session/memorilla/{id}")
	public List<MemorillaResult> getAllMemorillaResultsBySessionId(@PathVariable(name = "id")Long id) {
		return this.service.getAllMemorillaResultsBySessionId(id);
	}
}
