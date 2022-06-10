package com.umr.agilmentecore.Persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.HayUnoRepetidoResult;
import com.umr.agilmentecore.Class.MemorillaResult;
import com.umr.agilmentecore.Class.IntermediateClasses.MemorillaResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.ResultsListView;

@Repository
public interface MemorillaResultRepository extends org.springframework.data.repository.Repository<MemorillaResult, Long> {
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
			+ "r.mistakesPerLevel, "
			+ "r.successesPerLevel, "
			+ "r.streak, "
			+ "r.timePerLevel,"
			+ "r.totalTime, "
			+ "r.score, "
			+ "CONCAT(p.patient.firstName, ' ', p.patient.lastName), "
			+ "mS.game.name) "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.memorillaSession mS "
			+ "JOIN mS.results r "
			+ "ORDER BY r.completeDatetime")
	List<ResultsListView> findAllResultsListView();
	
	/**
	 * Busca un resultado de MemorillaResult a partir del id.
	 * @param id Id del resultado.
	 * @return Vista del detalle del resultado.
	 */
	@Query(value = "SELECT new com.umr.agilmentecore.Class.IntermediateClasses.MemorillaResultDetailView( "
			+ "r.id, "
			+ "r.completeDatetime, "
			+ "r.canceled, "
			+ "r.mistakesPerLevel, "
			+ "r.successesPerLevel, "
			+ "r.streak, "
			+ "r.timePerLevel,"
			+ "r.totalTime, "
			+ "r.score, "
			+ "CONCAT(p.patient.firstName, ' ', p.patient.lastName), "
			+ "mS.game.name, "
			+ "mS) "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.memorillaSession mS "
			+ "JOIN mS.results r "
			+ "WHERE r.id = ?1")
	MemorillaResultDetailView findMemorillaResultDetailById(Long id);
	
	/**
	 * Busca todos los resultados de MemorillaResult a partir
	 * del id del paciente.
	 * @param id ID del paciente.
	 * @return Lista de resultados.
	 */
	@Query(value = "SELECT "
			+ "r "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.memorillaSession mS "
			+ "JOIN mS.results r "
			+ "WHERE p.patient.id = ?1")
	List<MemorillaResult> findMemorillaResultByPatient_id(Long id);

	/**
	 * Busca todos los resultados de una planning
	 * @param planningId id de planning
	 * @return Lista de resultados.
	 */
	@Query(value = "SELECT new com.umr.agilmentecore.Class.IntermediateClasses.ResultsListView( "
			+ "r.id, "
			+ "r.completeDatetime, "
			+ "r.canceled, "
			+ "r.mistakesPerLevel, "
			+ "r.successesPerLevel, "
			+ "r.streak, "
			+ "r.timePerLevel,"
			+ "r.totalTime, "
			+ "r.score, "
			+ "CONCAT(p.patient.firstName, ' ', p.patient.lastName), "
			+ "mS.game.name) "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.memorillaSession mS "
			+ "JOIN mS.results r "
			+ "WHERE p.id = ?1 "
			+ "ORDER BY r.completeDatetime")
	List<ResultsListView> findAllResultsListFromPlanningView(Long planningId);

	/**
	 * Busca todos los resultados de MemorillaResult a partir
	 * del id de la sesión.
	 * @param id ID de la sesión..
	 * @return Lista de resultados.
	 */
	@Query(value = "SELECT "
			+ "r "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.memorillaSession mS "
			+ "JOIN mS.results r "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.memorillaSession mS "
			+ "JOIN mS.results r "
			+ "WHERE mS.id = ?1")
	List<MemorillaResult> findMemorillaResultByMemorillaSession_id(Long id);
}
