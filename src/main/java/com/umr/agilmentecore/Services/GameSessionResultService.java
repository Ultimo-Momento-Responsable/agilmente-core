package com.umr.agilmentecore.Services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.HayUnoRepetidoResult;
import com.umr.agilmentecore.Class.Planning;
import com.umr.agilmentecore.Class.PlanningDetail;
import com.umr.agilmentecore.Class.IntermediateClasses.ResultsData;
import com.umr.agilmentecore.Persistence.HayUnoRepetidoResultRepository;

@Service
public class GameSessionResultService {
	@Autowired
	private HayUnoRepetidoResultRepository repository;
	@Autowired
	private PlanningService planningService;
	
	public Page<HayUnoRepetidoResult> getAllResultsOrdered(Pageable page) {
		return this.repository.findAllByOrderByCompleteDatetimeDesc(page);
	}
	
	/**
	 * TODO: explicar que carajo hace esto
	 * @param games Lista con los datos y parámetros de los juegos.
	 * @return Lista de detalles de planificación.
	 * @throws Exception Si uno de los parámetros es inválido.
	 */
	public List<ResultsData> createResultList() {
		List<ResultsData> results = new ArrayList<ResultsData>();
		
		//Obtener todos los datos
		List<Planning> planningList = planningService.getAll();
		for(Planning specificPlanning : planningList) {
			
			List<PlanningDetail> detailList = specificPlanning.getDetail();
			for(PlanningDetail specificDetail: detailList) {
				
				List<ResultsData> resultList = specificDetail.getGameSession().getResults();
				for(ResultsData specificResult : resultList) {
					String game = specificDetail.getGameSession().getName();
					String patient = specificPlanning.getPatient().getFirstName() + " " + specificPlanning.getPatient().getLastName();
					specificResult.setGame(game);
					specificResult.setPatient(patient);
				}
				results.addAll(resultList);
			}
		}
		
		//Devolver la lista de los datos
		return results;
	}
}
