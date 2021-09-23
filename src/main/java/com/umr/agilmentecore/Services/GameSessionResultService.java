package com.umr.agilmentecore.Services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.EncuentraAlNuevoResult;
import com.umr.agilmentecore.Class.EncuentraAlNuevoSession;
import com.umr.agilmentecore.Class.HayUnoRepetidoResult;
import com.umr.agilmentecore.Class.HayUnoRepetidoSession;
import com.umr.agilmentecore.Class.PlanningDetail;
import com.umr.agilmentecore.Class.IntermediateClasses.EncuentraAlNuevoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.HayUnoRepetidoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.PatientResultsEncuentraAlNuevoView;
import com.umr.agilmentecore.Class.IntermediateClasses.PatientResultsView;
import com.umr.agilmentecore.Class.IntermediateClasses.ResultsListView;
import com.umr.agilmentecore.Persistence.EncuentraAlNuevoResultRepository;
import com.umr.agilmentecore.Persistence.EncuentraAlNuevoSessionRepository;
import com.umr.agilmentecore.Persistence.HayUnoRepetidoResultRepository;
import com.umr.agilmentecore.Persistence.HayUnoRepetidoSessionRepository;
import com.umr.agilmentecore.Persistence.PatientRepository;
import com.umr.agilmentecore.Persistence.PlanningDetailRepository;

@Service
public class GameSessionResultService {
	@Autowired
	private HayUnoRepetidoResultRepository hayUnoRepetidoResultRepository;
	@Autowired
	private HayUnoRepetidoSessionRepository hayUnoRepetidoSessionRepository;
	@Autowired
	private EncuentraAlNuevoSessionRepository encuentraAlNuevoSessionRepository;
	@Autowired
	private EncuentraAlNuevoResultRepository encuentraAlNuevoResultRepository;
	@Autowired
	private PlanningDetailRepository planningDetailRepository;
	@Autowired
	private PatientRepository patientRepository;
	/**
	 * Obtiene una página de resultados de todos los juegos.
	 * @param page Opciones de paginación.
	 * @return Página de resultados.
	 */
	public Page<ResultsListView> getAllResultsOrdered() {
		List<ResultsListView> hURResults = this.hayUnoRepetidoResultRepository.findAllResultsListView();
		List<ResultsListView> eANResults = this.encuentraAlNuevoResultRepository.findAllResultsListView();
		List<ResultsListView> results = Stream.concat(hURResults.stream(), eANResults.stream())
                .collect(Collectors.toList());
		return new PageImpl<>(results);
	}	
	
	/**
	 * Obtiene un resultado de HayUnoRepetido.
	 * @param Long el id del juego específico.
	 * @return Optional un resultado de un juego o nada.
	 */
	public HayUnoRepetidoResultDetailView getOneHayUnoRepetido(Long id) {
		return this.hayUnoRepetidoResultRepository.findHayUnoRepetidoResultDetailById(id);
	}
	
	/**
	 * Obtiene un resultado de EncuentraAlNuevo.
	 * @param Long el id del juego específico.
	 * @return Optional un resultado de un juego o nada.
	 */
	public EncuentraAlNuevoResultDetailView getOneEncuentraAlNuevo(Long id) {
		return this.encuentraAlNuevoResultRepository.findEncuentraAlNuevoResultDetailById(id);
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
		PlanningDetail pd = planningDetailRepository.findByEncuentraAlNuevoSession_id(result.getEncuentraAlNuevoSessionId());
		if (pd.getMaxNumberOfSessions() != -1 && !eANR.isCanceled()) {
			pd.setNumberOfSessions(pd.getNumberOfSessions() - 1);
			planningDetailRepository.save(pd);
		}
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
		PlanningDetail pd = planningDetailRepository.findByHayUnoRepetidoSession_id(result.getHayUnoRepetidoSessionId());
		if (pd.getMaxNumberOfSessions() != -1 && !hURR.isCanceled()) {
			pd.setNumberOfSessions(pd.getNumberOfSessions() - 1);
			planningDetailRepository.save(pd);
		}
		hayUnoRepetidoSessionRepository.save(hURS);
	}
  /**
	 * Obtiene una lista de todos los resultados
	 * a partir del id de un paciente.
	 * @param id ID del paciente.
	 * @return Devuelve la lista con la vista de los resultados.
	 */
	public PatientResultsView getAllResultsByPatient(Long id) {
		if (!this.patientRepository.findById(id).isEmpty()) {
			List<HayUnoRepetidoResult> hayUnoRepetidoResults = this.hayUnoRepetidoResultRepository.findHayUnoRepetidoResultByPatient_id(id);
			return new PatientResultsView(
					hayUnoRepetidoResults,
					new PatientResultsEncuentraAlNuevoView());
		} else {
			return null;
		}
	}
}
