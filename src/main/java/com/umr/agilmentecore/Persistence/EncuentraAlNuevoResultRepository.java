package com.umr.agilmentecore.Persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.EncuentraAlNuevoResult;
import com.umr.agilmentecore.Class.IntermediateClasses.EncuentraAlNuevoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.ResultsListView;

@Repository
public interface EncuentraAlNuevoResultRepository extends org.springframework.data.repository.Repository<EncuentraAlNuevoResult, Long> {
	// TODO: Pasar esto a un repositorio genérico de resultados.
	/**
	 * Obtiene todos los resultados de todos los juegos
	 * paginados.
	 * @param page Opciones de paginación.
	 * @return Una página de resultados genéricos.
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
			+ "CONCAT(p.patient.firstName, ' ', p.patient.lastName), "
			+ "eans.game.name) "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.encuentraAlNuevoSession eans "
			+ "JOIN eans.results r "
			+ "ORDER BY r.completeDatetime")
	List<ResultsListView> findAllResultsListView();
	
	/**
	 * Busca un resultado de HayUnoRepetidoResult a partir del id.
	 * @param id Id del resultado.
	 * @return Vista del detalle del resultado.
	 */
	@Query(value = "SELECT new com.umr.agilmentecore.Class.IntermediateClasses.EncuentraAlNuevoResultDetailView( "
			+ "r.id, "
			+ "r.completeDatetime, "
			+ "r.canceled, "
			+ "r.mistakes, "
			+ "r.successes, "
			+ "r.timeBetweenSuccesses,"
			+ "r.totalTime, "
			+ "r.score, "
			+ "CONCAT(p.patient.firstName, ' ', p.patient.lastName), "
			+ "eans.game.name, "
			+ "eans) "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.encuentraAlNuevoSession eans "
			+ "JOIN eans.results r "
			+ "WHERE r.id = ?1")
	EncuentraAlNuevoResultDetailView findEncuentraAlNuevoResultDetailById(Long id);
	
	/**
	 * Busca todos los resultados de EncuentraAlNuevo a partir
	 * del id del paciente.
	 * @param id ID del paciente.
	 * @return Lista de resultados.
	 */
	@Query(value = "SELECT "
			+ "r "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.encuentraAlNuevoSession eans "
			+ "JOIN eans.results r "
			+ "WHERE p.patient.id = ?1")
	List<EncuentraAlNuevoResult> findEncuentraAlNuevoResultByPatient_id(Long id);
	
	/**
	 * Busca todos los resultados de una planning
	 * @param planningId id de planning
	 * @return Lista de resultados.
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
			+ "CONCAT(p.patient.firstName, ' ', p.patient.lastName), "
			+ "eans.game.name) "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.encuentraAlNuevoSession eans "
			+ "JOIN eans.results r "
			+ "WHERE p.id = ?1 "
			+ "ORDER BY r.completeDatetime")
	List<ResultsListView> findAllResultsListFromPlanningView(Long planningId);

	/**
	 * Busca todos los resultados de EncuentraAlNuevoResult a partir
	 * del id de la sesión.
	 * @param id ID de la sesión..
	 * @return Lista de resultados.
	 */
	@Query(value = "SELECT "
			+ "r "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.encuentraAlNuevoSession eans "
			+ "JOIN eans.results r "
			+ "WHERE eans.id = ?1")
	List<EncuentraAlNuevoResult> findEncuentraAlNuevoResultByEncuentraAlNuevoSession_id(Long id);

	/**
	 * Busca el puntaje máximo de Encuentra al Nuevo en una dificultad.
	 * @return Puntaje máximo.
	 */
	@Query(value = "SELECT "
			+ "MAX(r.score) "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.encuentraAlNuevoSession eans "
			+ "JOIN eans.results r "
			+ "WHERE pd.difficulty = ?1")
	Integer findMaxScoreByDifficulty(String difficulty);
	
	/**
	 * Busca el puntaje mínimo de Encuentra al Nuevo en una dificultad.
	 * @return Puntaje mínimo.
	 */
	@Query(value = "SELECT "
			+ "MIN(r.score) "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.encuentraAlNuevoSession eans "
			+ "JOIN eans.results r "
			+ "WHERE pd.difficulty = ?1")
	Integer findMinScoreByDifficulty(String difficulty);
}
