package com.umr.agilmentecore.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.Game;
import com.umr.agilmentecore.Class.HayUnoRepetidoSession;
import com.umr.agilmentecore.Class.Planning;
import com.umr.agilmentecore.Class.PlanningDetail;
import com.umr.agilmentecore.Class.GameSessionBuilder.DirectorGameSessionBuilder;
import com.umr.agilmentecore.Class.GameSessionBuilder.HayUnoRepetidoSessionBuilder;
import com.umr.agilmentecore.Class.GameSessionBuilder.IGameSessionBuilder;
import com.umr.agilmentecore.Class.IntermediateClasses.GameData;
import com.umr.agilmentecore.Class.IntermediateClasses.ParamData;
import com.umr.agilmentecore.Class.IntermediateClasses.PlanningData;
import com.umr.agilmentecore.Persistence.PlanningRepository;

@Service
public class PlanningService {
	@Autowired
	private PlanningRepository repository;
	@Autowired
	private ProfessionalService professionalService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private GameService gameService;

	/**
	 * Obtiene todas las planificaciones paginadas.
	 * @param page Opciones de paginación.
	 * @return Página de planificaciones.
	 */
	public Page<Planning> getAll(Pageable page) {
		return this.repository.findAll(page);
	}

	/**
	 * Crea una planificación.
	 * @param planning Datos de la planificación.
	 * @return Una instancia de planificación.
	 */
	public Planning save(PlanningData planningData) {
		Planning planning = new Planning();
		
		planning.setProfessional(this.professionalService.getOne(planningData.getPatientId()).get());
		planning.setPatient(this.patientService.getOne(planningData.getPatientId()).get());
		
		planning.setStartDate(planningData.getStartDate());
		planning.setDueDate(planningData.getDueDate());
		planning.setCreationDatetime(new Date());
		
		List<PlanningDetail> detail = new ArrayList<>();
		
		for(GameData gameData : planningData.getGames()) {
			PlanningDetail planningDetail = new PlanningDetail();
			planningDetail.setMaxNumberOfSessions(gameData.getMaxNumberOfSessions());
			
			Game game = this.gameService.getOne(gameData.getGameId()).get();
			
			IGameSessionBuilder builder = this.getBuilder(game);
			DirectorGameSessionBuilder director = new DirectorGameSessionBuilder(builder);
			Map<String, String> params = this.createParamsMap(gameData.getParams());
			director.build(game, params);
			
			planningDetail.setHayUnoRepetidoSession((HayUnoRepetidoSession) builder.getGameSession());
			
			detail.add(planningDetail);
		}
		
		planning.setDetail(detail);
		
		return this.repository.save(planning);
	}
	
	private IGameSessionBuilder getBuilder(Game game) {
		switch(game.getId()) {
			default:
				return (IGameSessionBuilder) new HayUnoRepetidoSessionBuilder();
		}
	}
	
	private Map<String, String> createParamsMap(List<ParamData> params) {
		Map<String, String> map = new HashMap<String, String>();
		
		for(ParamData param : params) {
			map.put(param.getName(), param.getValue());
		}
		
		return map;
	}
	
}
