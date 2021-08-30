package com.umr.agilmentecore.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.HayUnoRepetidoResult;
import com.umr.agilmentecore.Class.IntermediateClasses.HayUnoRepetidoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.PatientResultsEncuentraAlNuevoView;
import com.umr.agilmentecore.Class.IntermediateClasses.PatientResultsHayUnoRepetidoView;
import com.umr.agilmentecore.Class.IntermediateClasses.PatientResultsView;
import com.umr.agilmentecore.Class.IntermediateClasses.ResultsListView;
import com.umr.agilmentecore.Persistence.HayUnoRepetidoResultRepository;

@Service
public class GameSessionResultService {
	@Autowired
	private HayUnoRepetidoResultRepository hayUnoRepetidoResultRepository;
	
	/**
	 * Obtiene una página de resultados de todos los juegos.
	 * @param page Opciones de paginación.
	 * @return Página de resultados.
	 */
	public Page<ResultsListView> getAllResultsOrdered(Pageable page) {
		return this.hayUnoRepetidoResultRepository.findAllResultsListView(page);
	}	
	
	/**
	 * Obtiene un resultado de HayUnoRepetido.
	 * @param Long el id del juego específico.
	 * @return Optional un resultado de un juego o nada.
	 */
	public Optional<HayUnoRepetidoResultDetailView> getOneHayUnoRepetido(Long id) {
		return this.hayUnoRepetidoResultRepository.findHayUnoRepetidoResultDetailById(id);
	}

	/**
	 * Obtiene una lista de todos los resultados
	 * a partir del id de un paciente.
	 * @param id ID del paciente.
	 * @return Devuelve la lista con la vista de los resultados.
	 */
	public PatientResultsView getAllResultsByPatient(Long id) {
		List<HayUnoRepetidoResult> hayUnoRepetidoResults = this.hayUnoRepetidoResultRepository.findHayUnoRepetidoResultByPatient_id(id);
		return new PatientResultsView(
				hayUnoRepetidoResults,
				new PatientResultsEncuentraAlNuevoView());
	}
	
	

}
