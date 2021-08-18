package com.umr.agilmentecore.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.HayUnoRepetidoResult;
import com.umr.agilmentecore.Class.HayUnoRepetidoSession;
import com.umr.agilmentecore.Class.Planning;
import com.umr.agilmentecore.Class.PlanningDetail;
import com.umr.agilmentecore.Class.IntermediateClasses.HayUnoRepetidoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.ResultsListView;
import com.umr.agilmentecore.Persistence.HayUnoRepetidoResultRepository;

@Service
public class GameSessionResultService {
	@Autowired
	private HayUnoRepetidoResultRepository hayUnoRepetidoResultRepository;
	@Autowired
	private PlanningService planningService;
	
	public List<ResultsListView> getAllResultsOrdered() {
		return this.hayUnoRepetidoResultRepository.findAllResultsListView();
	}

	/**
	 * Busca desde las planificaciones hacia los detalles y resultados y obtiene
	 * los datos necesarios para la página de resultados generales.
	 * @param ResultsListView Datos que estan incluidos en los resultados.
	 * @return Lista completa de resultados con pacientes, profesionales y datos asociados
	 */
//	public List<ResultsListView> createResultList() {
//		List<ResultsListView> results = new ArrayList<ResultsListView>();
//		
//		//Obtener todos los datos a traves de las planificaciones hacia los resultados
//		List<Planning> planningList = planningService.getAll();
//		for(Planning specificPlanning : planningList) {
//			
//			List<PlanningDetail> detailList = specificPlanning.getDetail();
//			for(PlanningDetail specificDetail: detailList) {
//				
//				List<ResultsListView> resultList = specificDetail.getGameSession().getResults();
//				for(ResultsListView specificResult : resultList) {
//					String game = specificDetail.getGameSession().getName();
//					String patient = specificPlanning.getPatient().getFirstName() + " " + specificPlanning.getPatient().getLastName();
//					specificResult.setGame(game);
//					specificResult.setPatient(patient);
//				}
//				
//				// Agregamos el resultado a la lista final
//				results.addAll(resultList);
//			}
//		}
//		
//		//Devolver la lista de los datos
//		return results;
//	}
	
	/**
	 * Obtiene un resultado de HayUnoRepetido.
	 * @param Long el id del juego específico.
	 * @return Optional un resultado de un juego o nada.
	 */
	public Optional<HayUnoRepetidoResultDetailView> getOneHayUnoRepetido(Long id) {
		return this.hayUnoRepetidoResultRepository.findHayUnoRepetidoResultDetailById(id);
	}

}
