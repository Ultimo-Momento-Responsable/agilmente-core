package com.umr.agilmentecore.Services;

import java.util.ArrayList;
import java.util.Comparator;
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
import com.umr.agilmentecore.Class.Planning;
import com.umr.agilmentecore.Class.PlanningDetail;
import com.umr.agilmentecore.Class.PlanningState;
import com.umr.agilmentecore.Class.IntermediateClasses.EncuentraAlNuevoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.HayUnoRepetidoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.PatientResultsView;
import com.umr.agilmentecore.Class.IntermediateClasses.ResultListHistory;
import com.umr.agilmentecore.Class.IntermediateClasses.ResultsListView;
import com.umr.agilmentecore.Persistence.EncuentraAlNuevoResultRepository;
import com.umr.agilmentecore.Persistence.EncuentraAlNuevoSessionRepository;
import com.umr.agilmentecore.Persistence.HayUnoRepetidoResultRepository;
import com.umr.agilmentecore.Persistence.HayUnoRepetidoSessionRepository;
import com.umr.agilmentecore.Persistence.PatientRepository;
import com.umr.agilmentecore.Persistence.PlanningDetailRepository;
import com.umr.agilmentecore.Persistence.PlanningRepository;
import com.umr.agilmentecore.Persistence.PlanningStateRepository;

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
	private PlanningRepository planningRepository;
	@Autowired
	private PlanningService planningService;
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
		Comparator<ResultsListView> comparator = (c1, c2) -> {
			return Long.valueOf(c1.getCompleteDatetime().getTime()).compareTo(c2.getCompleteDatetime().getTime()) * -1;
		};
		results.sort(comparator);
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
		eANR.setScore(result.getScore());
		eANS.addResult(eANR);
		PlanningDetail pd = planningDetailRepository.findByEncuentraAlNuevoSession_id(result.getEncuentraAlNuevoSessionId());
		Planning p = planningRepository.findByPlanningDetail(pd).get();
		if (pd.getMaxNumberOfSessions() != -1 && !eANR.isCanceled()) {
			pd.setNumberOfSessions(pd.getNumberOfSessions() - 1);
			planningDetailRepository.save(pd);
		}
		planningService.checkIfCompleted(p);
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
		hURR.setScore(result.getScore());
		hURS.addResult(hURR);
		PlanningDetail pd = planningDetailRepository.findByHayUnoRepetidoSession_id(result.getHayUnoRepetidoSessionId());
		Planning p = planningRepository.findByPlanningDetail(pd).get();
		if (pd.getMaxNumberOfSessions() != -1 && !hURR.isCanceled()) {
			pd.setNumberOfSessions(pd.getNumberOfSessions() - 1);
			planningDetailRepository.save(pd);
		}
		planningService.checkIfCompleted(p);
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
			List<EncuentraAlNuevoResult> encuentraAlNuevoResults = this.encuentraAlNuevoResultRepository.findEncuentraAlNuevoResultByPatient_id(id);
			return new PatientResultsView(
					hayUnoRepetidoResults,
					encuentraAlNuevoResults
					);
		} else {
			return null;
		}
	}

	/**
	 * Obtiene una lista de los resultados de un paciente ordenados por fecha
	 * El formato del resultado es del formato ResultListHistory
	 * @param id
	 * @return Devuelve una lista con los resultados.
	 */
	public List<ResultListHistory> getAllResultsByPatientOrdered(Long id) {
		List<HayUnoRepetidoResult> hayUnoRepetidoResults = this.hayUnoRepetidoResultRepository.findHayUnoRepetidoResultByPatient_id(id);
		List<EncuentraAlNuevoResult> encuentraAlNuevoResults = this.encuentraAlNuevoResultRepository.findEncuentraAlNuevoResultByPatient_id(id);
		List<ResultListHistory> results = new ArrayList<ResultListHistory>();
		for (HayUnoRepetidoResult h : hayUnoRepetidoResults) {
			ResultListHistory result = new ResultListHistory();
			result.setCompleteDatetime(h.getCompleteDatetime());
			result.setGame("Encuentra al Repetido");
			result.setScore(h.getScore());
			results.add(result);
		}
		for (EncuentraAlNuevoResult e : encuentraAlNuevoResults) {
			ResultListHistory result = new ResultListHistory();
			result.setCompleteDatetime(e.getCompleteDatetime());
			result.setGame("Encuentra al Nuevo");
			result.setScore(e.getScore());
			results.add(result);
		}
		Comparator<ResultListHistory> comparator = (c1, c2) -> {
			return Long.valueOf(c1.getCompleteDatetime().getTime()).compareTo(c2.getCompleteDatetime().getTime()) * -1;
		};
		results.sort(comparator);
		return results;
	}
}
