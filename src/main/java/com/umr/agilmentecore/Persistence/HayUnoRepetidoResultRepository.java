package com.umr.agilmentecore.Persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.HayUnoRepetidoResult;
import com.umr.agilmentecore.Class.IntermediateClasses.HayUnoRepetidoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.ResultsListView;

@Repository
public interface HayUnoRepetidoResultRepository extends org.springframework.data.repository.Repository<HayUnoRepetidoResult, Long> {
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
			+ "CONCAT(p.patient.firstName, ' ', p.patient.lastName), "
			+ "hurs.game.name) "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.hayUnoRepetidoSession hurs "
			+ "JOIN hurs.results r "
			+ "ORDER BY r.completeDatetime")
	Page<ResultsListView> findAllResultsListView(Pageable page);
	
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
			+ "CONCAT(p.patient.firstName, ' ', p.patient.lastName), "
			+ "hurs.game.name, "
			+ "hurs) "
			+ "FROM Planning p "
			+ "JOIN p.detail pd "
			+ "JOIN pd.hayUnoRepetidoSession hurs "
			+ "JOIN hurs.results r "
			+ "WHERE r.id = ?1")
	Optional<HayUnoRepetidoResultDetailView> findHayUnoRepetidoResultDetailById(Long id);
}
