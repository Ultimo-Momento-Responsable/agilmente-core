package com.umr.agilmentecore.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.Game;
import com.umr.agilmentecore.Class.Patient;
import com.umr.agilmentecore.Class.Planning;
import com.umr.agilmentecore.Class.PlanningDetail;
import com.umr.agilmentecore.Class.PlanningState;
import com.umr.agilmentecore.Class.Professional;
import com.umr.agilmentecore.Class.GameSessionBuilder.DirectorGameSessionBuilder;
import com.umr.agilmentecore.Class.GameSessionBuilder.EncuentraAlNuevoSessionBuilder;
import com.umr.agilmentecore.Class.GameSessionBuilder.HayUnoRepetidoSessionBuilder;
import com.umr.agilmentecore.Class.GameSessionBuilder.IGameSessionBuilder;
import com.umr.agilmentecore.Class.GameSessionBuilder.MemorillaSessionBuilder;
import com.umr.agilmentecore.Class.IntermediateClasses.GameData;
import com.umr.agilmentecore.Class.IntermediateClasses.PlanningData;
import com.umr.agilmentecore.Class.IntermediateClasses.PlanningMobileData;
import com.umr.agilmentecore.Class.IntermediateClasses.PlanningOverview;
import com.umr.agilmentecore.Class.IntermediateClasses.PlanningWithSessions;
import com.umr.agilmentecore.Class.IntermediateClasses.PlanningWithSessionsList;
import com.umr.agilmentecore.Interfaces.IGameSession;
import com.umr.agilmentecore.Interfaces.IParam;
import com.umr.agilmentecore.Persistence.PlanningRepository;
import com.umr.agilmentecore.Persistence.PlanningStateRepository;

@Service
public class PlanningService {
	@Autowired
	private PlanningRepository repository;
	@Autowired
	private PlanningStateRepository stateRepository;
	@Autowired
	private GameSessionResultService gameSessionResultService;
	@Autowired
	private ProfessionalService professionalService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private GameService gameService;

	/**
	 * Actualiza los estados de las plannings según su fecha;
	 */
	private void updateAllPlannings() {
		List<Planning> plannings = this.repository.findAll();
		for (Planning planning : plannings) {
			boolean isNotCanceled = planning.getState().getId()!=4;
			if (isNotCanceled) {
				if (isPending(planning)) {
					planning.setState(stateRepository.getOne((long) 2));
				}
				if (isActiveOrPending(planning)) {
					planning.setState(stateRepository.getOne((long) 3));
					planning.setMgp(gameSessionResultService.getAverageMGPFromPlanning(planning.getId()));			
				}
				if (isActiveWithUnlimitedGames(planning)) {
					planning.setState(stateRepository.getOne((long) 5));
				}
				if (isCompleted(planning)) {
					planning.setState(stateRepository.getOne((long) 6));
					planning.setMgp(gameSessionResultService.getAverageMGPFromPlanning(planning.getId()));					
				}
				this.repository.save(planning);
			}
		}
	}

	/**
	 * Cambia el estado de una planning a Cancelada
	 * @param planning la planning a cancelar
	 */
	public void cancelPlanning(Planning planning) { 
		planning.setState(stateRepository.getOne((long) 4));
		this.repository.save(planning);
	}
	
	/**
	 * Obtiene todas las planificaciones vigentes y pendientes de vista general (sin juegos)
	 * @return Página de planificaciones vigentes o pendientes, sin juegos.
	 */
	public List<PlanningOverview> getPlanningOverview() {
		updateAllPlannings();
		List<Planning> plannings = this.repository.findByState_nameOrState_name("Vigente", "Pendiente");
		List<PlanningOverview> listOverview = new ArrayList<PlanningOverview>();
		listOverview = planningToPlanningOverview(plannings, listOverview);
		
		return listOverview;
	}
	
	/**
	 * Obtiene las planning filtradas
	 * @param search búsqueda realizada
	 * @return Lista con todas las plannings filtradas
	 */
	public List<PlanningOverview> getAllFiltered(String search, List<String> states, Long patientId) {
		updateAllPlannings();
		String searchLower = search.toLowerCase();
		List<Planning> plannings = new ArrayList<Planning>();
		if (patientId != null) {
			plannings = this.repository.findFiltered(searchLower, patientId);
		} else {
			plannings = this.repository.findFiltered(searchLower);
		}
		
		List<Planning> effectivePlannings;
		
		if (states.isEmpty()) {
			effectivePlannings = plannings;
		} else {
			effectivePlannings = new ArrayList<Planning>();
			for (Planning p : plannings) {
				PlanningState pS = p.getState();
				if (states.contains(pS.getName())) {
					effectivePlannings.add(p);
				}
			}
		}
				
		List<PlanningOverview> listOverview = new ArrayList<PlanningOverview>();
		listOverview = planningToPlanningOverview(effectivePlannings, listOverview);
		return listOverview;
	}	
	
	/**
	 * Convierte una lista de plannings en una lista de planningOverview
	 * @param plannings 
	 * @param listOverview
	 * @return Una lista de planningOverview
	 */
	private List<PlanningOverview> planningToPlanningOverview(List<Planning> plannings, List<PlanningOverview> listOverview){
		for (Planning planning : plannings) {
			PlanningOverview pageableOverview = new PlanningOverview();
			pageableOverview.setPlanningId(planning.getId());
			pageableOverview.setPlanningName(planning.getName());
			pageableOverview.setStartDate(planning.getStartDate());
			pageableOverview.setDueDate(planning.getDueDate());
			pageableOverview.setPatientName(planning.getPatient().getFirstName() + " " + planning.getPatient().getLastName());
			pageableOverview.setProfessionalName(planning.getProfessional().getFirstName() + " " + planning.getProfessional().getLastName());
			pageableOverview.setStateName(planning.getState().getName());
			
			listOverview.add(pageableOverview);
		}
		return listOverview;
	}
	
	/**
	 * Obtiene todas las planificaciones sin paginar.
	 * @return Página de planificaciones.
	 */
	public List<Planning> getAll() {
		updateAllPlannings();
		return this.repository.findAll();
	}
	

	/**
	 * Crea una planificación.
	 * @param planningData Datos de la planificación.
	 * @return Una instancia de planificación.
	 * @throws Exception Si uno de los parámetros es inválido.
	 */
	public Planning save(PlanningData planningData) throws Exception {
		Planning planning = new Planning();
		planning.setProfessional(this.professionalService.getOne(planningData.getProfessionalId()).get());
		planning.setPatient(this.patientService.getOne(planningData.getPatientId()).get());
		
		planning.setState(this.stateRepository.getOne(planningData.getStateId()));
		
		planning.setName(planningData.getPlanningName());
		
		planning.setStartDate(planningData.getStartDate());
		planning.setDueDate(planningData.getDueDate());
		planning.setCreationDatetime(new Date());
		planning.setMgp(null);
		
		List<PlanningDetail> detail = this.createPlanningDetailList(planningData.getGames());
		
		planning.setDetail(detail);
		
		return this.repository.save(planning);
	}
		
	/**
	 * Edita una planning vigente
	 * @param planning. planificacion con nuevos datos
	 * @param id. id de la planificación a editar
	 * @return Planificación editada.
	 */
	public Planning edit(PlanningData planning, Long id) {
		Planning planningToUpdate = this.repository.getOne(id);
		planningToUpdate.setName(planning.getPlanningName());
		planningToUpdate.setStartDate(planning.getStartDate());
		planningToUpdate.setDueDate(planning.getDueDate());
		return this.repository.save(planningToUpdate);	
	}
	
	/**
	 * Obtiene la clase concreta de builder adecuada en 
	 * base al id del juego y crea una instancia de la misma.
	 * @param game Juego.
	 * @return Instancia de IGameSessionBuilder.
	 */
	private IGameSessionBuilder getBuilder(Game game) {
		switch(game.getId()) {
			case 1:
				return (IGameSessionBuilder) new HayUnoRepetidoSessionBuilder();
			case 2: 
				return (IGameSessionBuilder) new EncuentraAlNuevoSessionBuilder();
			case 3: 
				return (IGameSessionBuilder) new MemorillaSessionBuilder();
			default:
				return null;
		}
	}
	
	/**
	 * Ejecuta el builder.
	 * @param game Juego.
	 * @param params Mapa con los parámetros de la sesión.
	 * @return La instancia de la sesión de juego.
	 * @throws Exception Si uno de los parámetros es inválido.
	 */
	private IGameSession buildGameSession(Game game, Map<String, String> params) throws Exception{
		IGameSessionBuilder builder = this.getBuilder(game);
		DirectorGameSessionBuilder director = new DirectorGameSessionBuilder(builder);
		director.build(game, params);
		
		return builder.getGameSession();
	}
	
	/**
	 * Crea una lista de PlanningDetail a partir de una lista
	 * de GameData.
	 * @param games Lista con los datos y parámetros de los juegos.
	 * @return Lista de detalles de planificación.
	 * @throws Exception Si uno de los parámetros es inválido.
	 */
	private List<PlanningDetail> createPlanningDetailList(List<GameData> games) throws Exception {
		List<PlanningDetail> planningDetailList = new ArrayList<>();
		
		for (GameData gameData : games) {
			Game game = this.gameService.getOne(gameData.getGameId()).get();
			Map<String, String> params = gameData.getParams();
			if (params.isEmpty()) {
				throw new Exception("There are no params on game session");
			}
			IGameSession gameSession = this.buildGameSession(game, params);
			PlanningDetail planningDetail = new PlanningDetail(gameSession, gameData.getMaxNumberOfSessions(), gameData.getDifficulty());			
			planningDetailList.add(planningDetail);
		}
		
		return planningDetailList;
	}

	/**
	 * Obtiene todas las planificaciones del paciente a partir de su id.
	 * @param id Id del paciente.
	 * @return Lista de planificaciones.
	 */
	public List<Planning> getCurrentPlanningsFromPatient(Long patientId) {
		updateAllPlannings();
		return this.repository.findByPatient_id(patientId);
	}
	
	/**
	 * Obtiene todas las planificaciones actualmente
	 * vigentes o pendientes del paciente a partir de su id.
	 * @param patientId
	 * @return
	 */
	public List<Planning> getCurrentAndPendingPlanningsFromPatient(Long patientId) {
		updateAllPlannings();
		return this.repository.findByPatient_IdWithTwoStates(patientId,"Vigente","Pendiente");
	}
	
	/**
	 * Obtiene todas las planificaciones actualmente
	 * vigentes del paciente a partir de su id.
	 * @param id Id del paciente.
	 * @return Lista de planificaciones.
	 */
	public PlanningWithSessionsList getCurrentPlanningsFromPatientForMobile(Long patientId) {
		updateAllPlannings();
		Date today = new Date();
		List<Planning> plannings = this.repository.findByPatient_IdAndStartDateBeforeAndDueDateAfterAndState_IdNot(patientId,today,today,Long.valueOf(4));
		List<PlanningWithSessions> pWS = new ArrayList<PlanningWithSessions>();
		for (Planning plan : plannings) {
			List<PlanningMobileData> planningList = new ArrayList<PlanningMobileData>();
			String game = null;
			int numberOfSession = -1;
			int totalGames = 0;
			int gamesPlayed = 0;
			boolean unlimited = false;
			for (PlanningDetail pd : plan.getDetail()) {
				if (pd.getMaxNumberOfSessions()>0) {
					totalGames += pd.getMaxNumberOfSessions();
					gamesPlayed += pd.getNumberOfSessions();
				}else{
					if (pd.getMaxNumberOfSessions()==-1) {
						unlimited = true;
					}
				}
				List<IParam> parameters = new ArrayList<IParam>();
				for (IParam param : pd.getGameSession().getSettedParams()) {
					if (param!=null) {
						parameters.add(param);
					}
				}
				Long gameSessionId = pd.getGameSession().getId();
				game = (pd.getGameSession().getName());
				numberOfSession =(pd.getNumberOfSessions());
				planningList.add(new PlanningMobileData(gameSessionId, game, numberOfSession, pd.getMaxNumberOfSessions(), parameters));
			}
			pWS.add(new PlanningWithSessions(plan.getId(), totalGames, totalGames - gamesPlayed, plan.getDueDate(), unlimited, planningList));
		}
		PlanningWithSessionsList planningList = new PlanningWithSessionsList(pWS);
		return planningList;
	}
	
	/**
	 * Verifica que una planificacion se encuentra en estado "Pendiente" y dentro del rango de fechas validas.
	 * @param planning page de planificacion para evaluar.
	 * @return booleano confirmando la comparacion.
	 */
	private boolean isPending(Planning planning) {
		Date today = new Date();
		return planning.getState().getName().equals("Pendiente")
				&& planning.getStartDate().before(today) && 
				planning.getDueDate().after(today);
	}
	
	/**
	 * Verifica que una planificacion se encuentra en estado "Pendiente" o "Vigente" y dentro del rango de fechas validas.
	 * @param planning page de planificacion para evaluar.
	 * @return booleano confirmando la comparacion.
	 */
	private boolean isActiveOrPending(Planning planning) {
		Date today = new Date();
		return (planning.getState().getId() == 1 || planning.getState().getId() == 2 || planning.getState().getId() == 5) 
				&& planning.getDueDate().before(today);
	}
	
	/**
	 * Chequea si la planificación está vigente y con juegos ilimitados.
	 * @param p Planificación a chequear
	 * @return verdadero o falso según si posee ese estado o no.
	 */
	private boolean isActiveWithUnlimitedGames(Planning p) {
		if (p.getState().getId()==2) {
			for (PlanningDetail pDetail : p.getDetail()) {
				if (pDetail.getNumberOfSessions()==-1) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Chequea si la planificación fue completada.
	 * @param planning Planificación a chequear
	 * @return verdadero o falso
	 */
	public boolean isCompleted(Planning planning) {
		if (planning.getState().getId()==2 || planning.getState().getId()==5) {
			for (PlanningDetail pDetail : planning.getDetail()) {
				if (pDetail.getNumberOfSessions()>0) {
					return false;
				}
			}
			Date today = new Date();
			if (planning.getState().getId()==5) {
				if (planning.getDueDate().before(today)) {			
					return true;
				} else {
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Obtiene una planificación.
	 * @param Long el id de la planificación específica.
	 * @return Optional Un planning data o nada.
	 */
	public PlanningData getOnePlanningData(Long id) {
		updateAllPlannings();
		Optional<Planning> optSpecificPlanning = this.repository.findById(id);
		Planning specificPlanning = optSpecificPlanning.get();
		
		Optional<Patient> patient = this.patientService.getOne(specificPlanning.getPatient().getId());
		Patient specificPatient = patient.get();
		Optional<Professional> professional = this.professionalService.getOne(specificPlanning.getProfessional().getId());
		Professional specificProfessional = professional.get();
		
		//Escarbamos los detalles de la planning
		List<PlanningMobileData> planningList = new ArrayList<PlanningMobileData>();
		for (PlanningDetail pd : specificPlanning.getDetail()) {
			List<IParam> parameters = new ArrayList<IParam>();
			for (IParam param : pd.getGameSession().getSettedParams()) {
				if (param!=null) {
					parameters.add(param);
				}
			}
			planningList.add(new PlanningMobileData(pd.getGameSession().getId(), pd.getGameSession().getName(), pd.getNumberOfSessions(), pd.getMaxNumberOfSessions(), parameters));
		}
		
		// Enviamos todo a la vista	
		PlanningData planningData = new PlanningData(
				specificPatient.getId(), specificPlanning.getName(),specificPatient.getFirstName(), specificPatient.getLastName(), specificPatient.getBornDate(),
				specificProfessional.getId(), specificProfessional.getFirstName(), specificProfessional.getLastName(),
				specificPlanning.getState().getName(), specificPlanning.getStartDate(), specificPlanning.getDueDate(), planningList);
		
		return planningData;
	}
	
	/**
	 * Obtiene una planificación.
	 * @param Long el id de la planificación específica.
	 * @return Optional Un planning data o nada.
	 */
	public Planning getOne(Long id) {
		updateAllPlannings();
		return repository.getOne(id);
	}
	
	/**
	 * Cancela una planificación
	 * @param Long el id de la planificación específica.
	 */
	public boolean cancel(Long id) {
		updateAllPlannings();
		Optional<Planning> optSpecificPlanning = this.repository.findById(id);
		Planning specificPlanning = optSpecificPlanning.get();
		if (specificPlanning.getState().getName().equals("Vigente") || 
			specificPlanning.getState().getName().equals("Vigente con juegos libres")){
			cancelPlanning(specificPlanning);
			return true;
		}
		if (specificPlanning.getState().getName().equals("Pendiente")) {
			this.repository.delete(specificPlanning);
			return true;
		}
		return false;
	}

	/**
	 * Lista todos los estados de las planificaciones.
	 * @return Lista con los estados.
	 */
	public List<PlanningState> getPlanningStates() {
		return stateRepository.findAll();
	}
	
	/**
	 * Obtiene una lista de MGPs de las plannings pertenecientes a un paciente
	 * @param patientId Id del paciente
	 * @return Lista de MGPs
	 */
	public List<Integer> getPlanningsMGPs(Long patientId) {
		return repository.getPlanningsMGPs(patientId);
	}
}
