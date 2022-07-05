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
import com.umr.agilmentecore.Class.MemorillaResult;
import com.umr.agilmentecore.Class.MemorillaSession;
import com.umr.agilmentecore.Class.Planning;
import com.umr.agilmentecore.Class.PlanningDetail;
import com.umr.agilmentecore.Class.IntermediateClasses.EncuentraAlNuevoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.HayUnoRepetidoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.MemorillaResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.PatientResultsView;
import com.umr.agilmentecore.Class.IntermediateClasses.ResultListHistory;
import com.umr.agilmentecore.Class.IntermediateClasses.ResultsListView;
import com.umr.agilmentecore.Persistence.EncuentraAlNuevoResultRepository;
import com.umr.agilmentecore.Persistence.EncuentraAlNuevoSessionRepository;
import com.umr.agilmentecore.Persistence.HayUnoRepetidoResultRepository;
import com.umr.agilmentecore.Persistence.HayUnoRepetidoSessionRepository;
import com.umr.agilmentecore.Persistence.MemorillaResultRepository;
import com.umr.agilmentecore.Persistence.MemorillaSessionRepository;
import com.umr.agilmentecore.Persistence.PatientRepository;
import com.umr.agilmentecore.Persistence.PlanningDetailRepository;
import com.umr.agilmentecore.Persistence.PlanningRepository;

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
	private MemorillaSessionRepository memorillaSessionRepository;
	@Autowired
	private MemorillaResultRepository memorillaResultRepository;
	@Autowired
	private PlanningDetailRepository planningDetailRepository;
	@Autowired
	private PlanningRepository planningRepository;
	@Autowired
	private PlanningService planningService;
	@Autowired
	private PatientRepository patientRepository;
	private static int MAX_VALUE_MGP = 2500;
	
	/**
	 * Obtiene una página de resultados de todos los juegos.
	 * @param page Opciones de paginación.
	 * @return Página de resultados.
	 */
	public Page<ResultsListView> getAllResultsOrdered() {
		List<ResultsListView> hURResults = this.hayUnoRepetidoResultRepository.findAllResultsListView();
		List<ResultsListView> eANResults = this.encuentraAlNuevoResultRepository.findAllResultsListView();
		List<ResultsListView> mResults = this.memorillaResultRepository.findAllResultsListView();
		List<ResultsListView> results = Stream.concat(hURResults.stream(), eANResults.stream())
                .collect(Collectors.toList());
		results = Stream.concat(results.stream(), mResults.stream())
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
	 * Obtiene un resultado de Memorilla.
	 * @param Long el id del juego específico.
	 * @return Optional un resultado de un juego o nada.
	 */
	public MemorillaResultDetailView getOneMemorilla(Long id) {
		return this.memorillaResultRepository.findMemorillaResultDetailById(id);
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
		eANR.setMgp(this.calculateMGPForEAN(pd.getDifficulty(), result.getScore()));
		Planning p = planningRepository.findByPlanningDetail(pd).get();
		if (pd.getMaxNumberOfSessions() != -1 && !eANR.isCanceled()) {
			pd.setNumberOfSessions(pd.getNumberOfSessions() - 1);
			planningDetailRepository.save(pd);
		}
		planningService.isCompleted(p);
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
		hURR.setMgp(this.calculateMGPForEAR(pd.getDifficulty(), result.getScore()));
		Planning p = planningRepository.findByPlanningDetail(pd).get();
		if (pd.getMaxNumberOfSessions() != -1 && !hURR.isCanceled()) {
			pd.setNumberOfSessions(pd.getNumberOfSessions() - 1);
			planningDetailRepository.save(pd);
		}
		planningService.isCompleted(p);
		hayUnoRepetidoSessionRepository.save(hURS);
	}
	
	/**
	 * Guarda un resultado de Memorilla
	 * @param result el resultado a guardar
	 */
	public void saveMemorilla(MemorillaResultDetailView result) {
		MemorillaSession mS = this.memorillaSessionRepository.getOne(result.getMemorillaSessionId());
		MemorillaResult mR = new MemorillaResult();
		mR.setMistakesPerLevel(result.getMistakesPerLevel());
		mR.setSuccessesPerLevel(result.getSuccessesPerLevel());
		mR.setStreak(result.getStreak());
		mR.setCanceled(result.isCanceled());
		mR.setCompleteDatetime(result.getCompleteDatetime());
		mR.setTimePerLevel(result.getTimePerLevel());
		mR.setTotalTime(result.getTotalTime());
		mR.setScore(result.getScore());
		mS.addResult(mR);
		PlanningDetail pd = planningDetailRepository.findByMemorillaSession_id(result.getMemorillaSessionId());
		mR.setMgp(this.calculateMGPForM(pd.getDifficulty(), result.getScore()));
		Planning p = planningRepository.findByPlanningDetail(pd).get();
		if (pd.getMaxNumberOfSessions() != -1 && !mR.isCanceled()) {
			pd.setNumberOfSessions(pd.getNumberOfSessions() - 1);
			planningDetailRepository.save(pd);
		}
		planningService.isCompleted(p);
		memorillaSessionRepository.save(mS);
		
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
	
	/**
	 * Busca todos los resultados de HayUnoRepetidoResult a partir
	 * del id de la sesión.
	 * @param id ID de la sesión..
	 * @return Lista de resultados.
	 */
	public List<HayUnoRepetidoResult> getAllHayUnoRepetidoResultsBySessionId(Long id) {
		return this.hayUnoRepetidoResultRepository.findHayUnoRepetidoResultByHayUnoRepetidoSession_id(id);
	}
	
	/**
	 * Busca todos los resultados de EncuentraAlNuevoResult a partir
	 * del id de la sesión.
	 * @param id ID de la sesión..
	 * @return Lista de resultados.
	 */
	public List<EncuentraAlNuevoResult> getAllEncuentraAlNuevoResultsBySessionId(Long id) {
		return this.encuentraAlNuevoResultRepository.findEncuentraAlNuevoResultByEncuentraAlNuevoSession_id(id);
	}

	/**
	 * Busca los resultados de todos los juegos pertenecientes a una planning
	 * @param planningId id de la planning a buscar
	 * @return Page de resultados de la planning.
	 */
	public Page<ResultsListView> getAllPlanningResultsOrdered(Long planningId) {
		List<ResultsListView> hURResults = this.hayUnoRepetidoResultRepository.findAllResultsListFromPlanningView(planningId);
		List<ResultsListView> eANResults = this.encuentraAlNuevoResultRepository.findAllResultsListFromPlanningView(planningId);
		List<ResultsListView> mResults = this.memorillaResultRepository.findAllResultsListFromPlanningView(planningId);
		
		List<ResultsListView> results = Stream.concat(hURResults.stream(), eANResults.stream())
                .collect(Collectors.toList());
		results = Stream.concat(results.stream(), mResults.stream())
                .collect(Collectors.toList());
		Comparator<ResultsListView> comparator = (c1, c2) -> {
			return Long.valueOf(c1.getCompleteDatetime().getTime()).compareTo(c2.getCompleteDatetime().getTime()) * -1;
		};
		results.sort(comparator);
		return new PageImpl<>(results);
	}


	/**
	 * Busca todos los resultados de MemorillaResult a partir
	 * del id de la sesión.
	 * @param id ID de la sesión.
	 * @return Lista de resultados.
	 */
	public List<MemorillaResult> getAllMemorillaResultsBySessionId(Long id) {
		return this.memorillaResultRepository.findMemorillaResultByMemorillaSession_id(id);
	}
	
	/**
	 * Calcula el MGP para el Encuentra al Repetido.
	 * @param difficulty Dificultad de la sesión.
	 * @param currentScore Puntaje para el cual se quiere 
	 * calcular el MGP.
	 * @return Valor del MGP.
	 */
	private int calculateMGPForEAR(String difficulty, int currentScore) {
		Integer maxScore = this.hayUnoRepetidoResultRepository.findMaxScoreByDifficulty(difficulty);
		Integer minScore = this.hayUnoRepetidoResultRepository.findMinScoreByDifficulty(difficulty);
		
		return this.calculateMGP(minScore, maxScore, currentScore);
	}
	
	/**
	 * Calcula el MGP para el Encuentra al Nuevo.
	 * @param difficulty Dificultad de la sesión.
	 * @param currentScore Puntaje para el cual se quiere 
	 * calcular el MGP.
	 * @return Valor del MGP.
	 */
	private int calculateMGPForEAN(String difficulty, int currentScore) {
		Integer maxScore = this.encuentraAlNuevoResultRepository.findMaxScoreByDifficulty(difficulty);
		Integer minScore = this.encuentraAlNuevoResultRepository.findMinScoreByDifficulty(difficulty);
		
		return this.calculateMGP(minScore, maxScore, currentScore);
	}
	
	/**
	 * Calcula el MGP para el Memorilla.
	 * @param difficulty Dificultad de la sesión.
	 * @param currentScore Puntaje para el cual se quiere 
	 * calcular el MGP.
	 * @return Valor del MGP.
	 */
	private int calculateMGPForM(String difficulty, int currentScore) {
		Integer maxScore = this.memorillaResultRepository.findMaxScoreByDifficulty(difficulty);
		Integer minScore = this.memorillaResultRepository.findMinScoreByDifficulty(difficulty);
		
		return this.calculateMGP(minScore, maxScore, currentScore);
	}
	
	/**
	 * Realiza el cálculo del MGP.
	 * @param minScore El puntaje más bajo que hay en la dificultad
	 * determinada en un juego.
	 * @param maxScor eEl puntaje más alto que hay en la dificultad
	 * determinada en un juego.
	 * @param currentScore El puntaje para el cual se está 
	 * calculando el MGP.
	 * @return El valor del MGP.
	 */
	private int calculateMGP(Integer minScore, Integer maxScore, int currentScore) {
		if (minScore == null || minScore - maxScore == 0) {
			return (int) Math.round(this.MAX_VALUE_MGP * 0.5);
		}
		
		int mgp = (int) Math.round(this.MAX_VALUE_MGP * ((float) (currentScore - minScore) / (maxScore - minScore)));
		
		if (mgp > this.MAX_VALUE_MGP) {
			return this.MAX_VALUE_MGP;
		}
		
		if (mgp < 0) {
			return 0;
		}
		
		return mgp;
	}
	
	public Integer get(int score) {
		return this.calculateMGPForEAR("Facil", score);
	}
	
	/**
	 * Busca los scores de los resultados de una sesión.
	 * @param game nombre del juego.
	 * @param id de la sesión de juego.
	 * @return lista de scores
	 */
	public List<Integer> getScoresFromSession(String game, Long id) {
		List<Integer> results = new ArrayList<Integer>();
		switch (game) {
			case "Encuentra al Nuevo": 
				results = encuentraAlNuevoResultRepository.findScoresBySessionId(id);
				break;
			case "Encuentra al Repetido":
				results = hayUnoRepetidoResultRepository.findScoresBySessionId(id);
				break;
			case "Memorilla":
				results = memorillaResultRepository.findScoresBySessionId(id);
				break;
		}
		return results;
	}
}
