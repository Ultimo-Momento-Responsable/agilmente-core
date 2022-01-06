package com.umr.agilmentecore.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.Game;
import com.umr.agilmentecore.Class.Patient;
import com.umr.agilmentecore.Class.Planning;
import com.umr.agilmentecore.Class.PlanningDetail;
import com.umr.agilmentecore.Class.Professional;
import com.umr.agilmentecore.Class.GameSessionBuilder.DirectorGameSessionBuilder;
import com.umr.agilmentecore.Class.GameSessionBuilder.EncuentraAlNuevoSessionBuilder;
import com.umr.agilmentecore.Class.GameSessionBuilder.HayUnoRepetidoSessionBuilder;
import com.umr.agilmentecore.Class.GameSessionBuilder.IGameSessionBuilder;
import com.umr.agilmentecore.Class.IntermediateClasses.GameData;
import com.umr.agilmentecore.Class.IntermediateClasses.PlanningData;
import com.umr.agilmentecore.Class.IntermediateClasses.PlanningList;
import com.umr.agilmentecore.Class.IntermediateClasses.PlanningMobileData;
import com.umr.agilmentecore.Class.IntermediateClasses.PlanningOverview;
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
			if (isPending(planning)) {
				planning.setState(stateRepository.getOne((long) 2));
			}
			if (isActiveOrPending(planning)) {
				planning.setState(stateRepository.getOne((long) 3));
			}
			this.repository.save(planning);
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
	 * Obtiene todas las planificaciones paginadas.
	 * @param page Opciones de paginación.
	 * @return Página de planificaciones.
	 */
	public Page<Planning> getAll(Pageable page) {
		updateAllPlannings();
		return this.repository.findAll(page);
	}
	
	/**
	 * Obtiene todas las planificaciones vigentes y pendientes de vista general (sin juegos)
	 * @return Página de planificaciones vigentes o pendientes, sin juegos.
	 */
	public Page<PlanningOverview> getPlanningOverview() {
		updateAllPlannings();
		List<Planning> plannings = this.repository.findByState_nameOrState_name("Vigente", "Pendiente");
		List<PlanningOverview> listOverview = new ArrayList<PlanningOverview>();
		listOverview = planningToPlanningOverview(plannings, listOverview);
		Page<PlanningOverview> pageOverview = new PageImpl<>(listOverview);
		
		return pageOverview;
	}
	
	/**
	 * Obtiene las planning filtradas
	 * @param search búsqueda realizada
	 * @return Lista con todas las plannings filtradas
	 */
	public Page<PlanningOverview> getPlanningsFiltered(String search) {
		updateAllPlannings();
		List<Planning> plannings = this.repository.findFiltered(search);
		List<PlanningOverview> listOverview = new ArrayList<PlanningOverview>();
		listOverview = planningToPlanningOverview(plannings, listOverview);
		Page<PlanningOverview> pageOverview = new PageImpl<>(listOverview);
		return pageOverview;
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
	 * @param page Opciones de paginación.
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
		
		List<PlanningDetail> detail = this.createPlanningDetailList(planningData.getGames());
		
		planning.setDetail(detail);
		
		return this.repository.save(planning);
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
			PlanningDetail planningDetail = new PlanningDetail(gameSession, gameData.getMaxNumberOfSessions());			
			planningDetailList.add(planningDetail);
		}
		
		return planningDetailList;
	}

	/**
	 * Obtiene todas las planificaciones actualmente
	 * vigentes del paciente a partir de su id.
	 * @param id Id del paciente.
	 * @return Lista de planificaciones.
	 */
	public List<Planning> getCurrentPlanningsFromPatient(Long patientId) {
		updateAllPlannings();
		return this.repository.findByPatient_idAndState_name(patientId,"Vigente");
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
	public PlanningList getCurrentPlanningsFromPatientForMobile(Long patientId) {
		updateAllPlannings();
		List<Planning> plannings = this.repository.findByPatient_idAndState_name(patientId,"Vigente");
		List<PlanningMobileData> planningList = new ArrayList<PlanningMobileData>();
		for (Planning plan : plannings) {
			String game = null;
			int numberOfSession = -1;
			for (PlanningDetail pd : plan.getDetail()) {
				List<IParam> parameters = new ArrayList<IParam>();
				if (pd.getNumberOfSessions() != 0) {
					for (IParam param : pd.getGameSession().getSettedParams()) {
						if (param!=null) {
							parameters.add(param);
						}
					}
					Long gameSessionId = pd.getGameSession().getId();
					game = (pd.getGameSession().getName());
					numberOfSession =(pd.getNumberOfSessions());
					planningList.add(new PlanningMobileData(gameSessionId, game, numberOfSession, parameters));
				}
			}
		}
		PlanningList pl = new PlanningList(planningList);
		return pl;
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
		return (planning.getState().getName().equals("Vigente") || planning.getState().getName().equals("Pendiente")) 
				&& planning.getDueDate().before(today);
	}
	
	/**
	 * Obtiene una planificación.
	 * @param Long el id de la planificación específica.
	 * @return Optional Un planning data o nada.
	 */
	public PlanningData getOne(Long id) {
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
			String game = (pd.getGameSession().getName());
			int numberOfSession =(pd.getNumberOfSessions());
			planningList.add(new PlanningMobileData(game,numberOfSession, parameters));
		}
		
		// Enviamos todo a la vista	
		PlanningData planningData = new PlanningData(
				specificPatient.getId(), specificPlanning.getName(),specificPatient.getFirstName(), specificPatient.getLastName(),
				specificProfessional.getId(), specificProfessional.getFirstName(), specificProfessional.getLastName(),
				specificPlanning.getState().getName(), specificPlanning.getStartDate(), specificPlanning.getDueDate(), planningList);
		
		return planningData;
	}
	
	/**
	 * Cancela una planificación
	 * @param Long el id de la planificación específica.
	 */
	public boolean cancel(Long id) {
		updateAllPlannings();
		Optional<Planning> optSpecificPlanning = this.repository.findById(id);
		Planning specificPlanning = optSpecificPlanning.get();
		if (specificPlanning.getState().getName().equals("Pendiente") || 
				specificPlanning.getState().getName().equals("Vigente")) {
			cancelPlanning(specificPlanning);
			return true;
		}
		return false;
	}
}
