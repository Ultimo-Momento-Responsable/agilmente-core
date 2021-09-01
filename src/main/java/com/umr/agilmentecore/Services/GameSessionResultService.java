package com.umr.agilmentecore.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.umr.agilmentecore.Class.EncuentraAlNuevoResult;
import com.umr.agilmentecore.Class.EncuentraAlNuevoSession;
import com.umr.agilmentecore.Class.HayUnoRepetidoResult;
import com.umr.agilmentecore.Class.HayUnoRepetidoSession;
import com.umr.agilmentecore.Class.IntermediateClasses.EncuentraAlNuevoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.HayUnoRepetidoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.PatientResultsEncuentraAlNuevoView;
import com.umr.agilmentecore.Class.IntermediateClasses.PatientResultsView;
import com.umr.agilmentecore.Class.IntermediateClasses.ResultsListView;
import com.umr.agilmentecore.Persistence.EncuentraAlNuevoSessionRepository;
import com.umr.agilmentecore.Persistence.HayUnoRepetidoResultRepository;
import com.umr.agilmentecore.Persistence.HayUnoRepetidoSessionRepository;

@Service
public class GameSessionResultService {
	@Autowired
	private HayUnoRepetidoResultRepository hayUnoRepetidoResultRepository;
	@Autowired
	private HayUnoRepetidoSessionRepository hayUnoRepetidoSessionRepository;
	@Autowired
	private EncuentraAlNuevoSessionRepository encuentraAlNuevoSessionRepository;
	
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
	 * Guarda un resultado de EncuentraAlNuevo
	 * @param result el resultado a guardar
	 */
	public void saveEncuentraAlNuevo(EncuentraAlNuevoResultDetailView result) {
		EncuentraAlNuevoSession eANS = this.encuentraAlNuevoSessionRepository.getOne(result.getEncuentraAlNuevoSessionId());
		EncuentraAlNuevoResult eANR = new EncuentraAlNuevoResult();
		eANR.setMistakes(result.getMistakes());
		eANR.setSuccesses(result.getSuccesses());
		eANR.setCanceled(result.isCanceled());
		eANR.setCompleteDatetime(result.getCompleteDatetime());
		eANR.setTimeBetweenSuccesses(result.getTimeBetweenSuccesses());
		eANR.setTotalTime(result.getTotalTime());
		eANS.addResult(eANR);
		encuentraAlNuevoSessionRepository.save(eANS);
	}

	/**
	 * Guarda un resultado de HayUnoRepetido
	 * @param result el resultado a guardar
	 */
	public void saveHayUnoRepetido(HayUnoRepetidoResultDetailView result) {
		HayUnoRepetidoSession hURS = this.hayUnoRepetidoSessionRepository.getOne(result.getHayUnoRepetidoSessionId());
		HayUnoRepetidoResult hURR = new HayUnoRepetidoResult();
		hURR.setMistakes(result.getMistakes());
		hURR.setSuccesses(result.getSuccesses());
		hURR.setCanceled(result.isCanceled());
		hURR.setCompleteDatetime(result.getCompleteDatetime());
		hURR.setTimeBetweenSuccesses(result.getTimeBetweenSuccesses());
		hURR.setTotalTime(result.getTotalTime());
		hURS.addResult(hURR);
		hayUnoRepetidoSessionRepository.save(hURS);
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
