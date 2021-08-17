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
	 * Busca desde las planificaciones hacia los detalles y resultados y obtiene
	 * los datos necesarios para la página de resultados generales.
	 * @param ResultsData Datos que estan incluidos en los resultados.
	 * @return Lista completa de resultados con pacientes, profesionales y datos asociados
	 */
	public List<ResultsData> createResultList() {
		List<ResultsData> results = new ArrayList<ResultsData>();
		
		//Obtener todos los datos a traves de las planificaciones hacia los resultados
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
				
				// Agregamos el resultado a la lista final
				results.addAll(resultList);
			}
		}
		
		//Devolver la lista de los datos
		return results;
	}
}
