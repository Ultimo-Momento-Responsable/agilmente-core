package com.umr.agilmentecore.Persistence;

import java.util.List;
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
			+ "JOIN hurs.results r")
	List<ResultsListView> findAllResultsListView();
	
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
