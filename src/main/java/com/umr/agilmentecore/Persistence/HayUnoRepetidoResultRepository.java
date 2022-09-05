package com.umr.agilmentecore.Persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.HayUnoRepetidoResult;
import com.umr.agilmentecore.Class.IntermediateClasses.HayUnoRepetidoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.ResultsListView;

@Repository
public interface HayUnoRepetidoResultRepository extends org.springframework.data.repository.Repository<HayUnoRepetidoResult, Long> {
	// TODO: Pasar esto a un repositorio genérico de resultados.
		
	/**
	 * Busca un resultado de HayUnoRepetidoResult a partir del id.
	 * @param id Id del resultado.
	 * @return Vista del detalle del resultado.
	 */
	@Query(value = "SELECT new com.umr.agilmentecore.Class.IntermediateClasses.HayUnoRepetidoResultDetailView( "
			+ "r.id, "
			+ "r.completeDatetime, "
			+ "r.canceled, "
			+ "r.mistakes, "
			+ "r.successes, "
			+ "r.timeBetweenSuccesses,"
			+ "r.totalTime, "
			+ "r.score, "
			+ "CONCAT(p.patient.firstName, ' ', p.patient.lastName), "
			+ "hurs.game.name, "
			+ "hurs) "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.hayUnoRepetidoSession hurs "
			+ "JOIN hurs.results r "
			+ "WHERE r.id = ?1")
	HayUnoRepetidoResultDetailView findHayUnoRepetidoResultDetailById(Long id);
	
	/**
	 * Busca todos los resultados de HayUnoRepetidoResult a partir
	 * del id del paciente.
	 * @param id ID del paciente.
	 * @return Lista de resultados.
	 */
	@Query(value = "SELECT "
			+ "r "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.hayUnoRepetidoSession hurs "
			+ "JOIN hurs.results r "
			+ "WHERE p.patient.id = ?1")
	List<HayUnoRepetidoResult> findHayUnoRepetidoResultByPatient_id(Long id);
	
	/**
	 * Obtiene todos los resultados de hay uno repetido
	 * listado.
	 * @return Una lista de resultados genéricos.
	 */
	@Query(value = "SELECT new com.umr.agilmentecore.Class.IntermediateClasses.ResultsListView( "
			+ "r.id, "
			+ "r.completeDatetime, "
			+ "r.canceled, "
			+ "r.mistakes, "
			+ "r.successes, "
			+ "r.timeBetweenSuccesses,"
			+ "r.totalTime, "
			+ "r.score, "
			+ "r.mgp, "
			+ "CONCAT(p.patient.firstName, ' ', p.patient.lastName), "
			+ "hurs.game.name) "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.hayUnoRepetidoSession hurs "
			+ "JOIN hurs.results r "
			+ "ORDER BY r.completeDatetime")
	List<ResultsListView> findAllResultsListView();
	
	/**
	 * Obtiene todos los resultados de una planning de hay uno repetido
	 * listados.
	 * @param planningId id de la planning
	 * @return Una lista de resultados genéricos.
	 */
	@Query(value = "SELECT new com.umr.agilmentecore.Class.IntermediateClasses.ResultsListView( "
			+ "r.id, "
			+ "r.completeDatetime, "
			+ "r.canceled, "
			+ "r.mistakes, "
			+ "r.successes, "
			+ "r.timeBetweenSuccesses,"
			+ "r.totalTime, "
			+ "r.score, "
			+ "r.mgp, "
			+ "CONCAT(p.patient.firstName, ' ', p.patient.lastName), "
			+ "hurs.game.name) "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.hayUnoRepetidoSession hurs "
			+ "JOIN hurs.results r "
			+ "WHERE p.id = ?1 "
			+ "ORDER BY r.completeDatetime")
	List<ResultsListView> findAllResultsListFromPlanningView(Long planningId);

	/**
	 * Busca todos los resultados de HayUnoRepetidoResult a partir
	 * del id de la sesión.
	 * @param id ID de la sesión..
	 * @return Lista de resultados.
	 */
	@Query(value = "SELECT "
			+ "r "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.hayUnoRepetidoSession hurs "
			+ "JOIN hurs.results r "
			+ "WHERE hurs.id = ?1")
	List<HayUnoRepetidoResult> findHayUnoRepetidoResultByHayUnoRepetidoSession_id(Long id);
	
	/**
	 * Busca el puntaje máximo de Encuentra al Repetido en una dificultad.
	 * @return Puntaje máximo.
	 */
	@Query(value = "SELECT "
			+ "MAX(r.score) "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.hayUnoRepetidoSession hurs "
			+ "JOIN hurs.results r "
			+ "WHERE pd.difficulty = ?1")
	Integer findMaxScoreByDifficulty(String difficulty);
	
	/**
	 * Busca el puntaje mínimo de Encuentra al Repetido en una dificultad.
	 * @return Puntaje mínimo.
	 */
	@Query(value = "SELECT "
			+ "MIN(r.score) "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.hayUnoRepetidoSession hurs "
			+ "JOIN hurs.results r "
			+ "WHERE pd.difficulty = ?1")
	Integer findMinScoreByDifficulty(String difficulty);
	
	/**
	 * Obtiene los scores de una sesion de Hay uno Repetido
	 * @param id de la sesión
	 * @return Lista de Scores.
	 */
	@Query(value = "SELECT r.score FROM HayUnoRepetidoSession hurs "
			+ "JOIN hurs.results r "
			+ "WHERE hurs.id = ?1 "
			+ "ORDER BY r.score DESC")
	List<Integer> findScoresBySessionId(Long id);
	
	/**
	 * Calcula el promedio de MGP de una planificación de hay uno repetido
	 * @param planningId id de la planning
	 * @return MGP promedio.
	 */
	@Query(value = "SELECT AVG(r.mgp) "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.hayUnoRepetidoSession hurs "
			+ "JOIN hurs.results r "
			+ "WHERE p.id = ?1 ")
	Integer getMGPAverageByPlanning(Long planningId);
}
