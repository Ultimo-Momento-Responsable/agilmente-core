package com.umr.agilmentecore.Persistence;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.Planning;
import com.umr.agilmentecore.Class.PlanningDetail;

@Repository
public interface PlanningRepository extends JpaRepository<Planning, Long> {
	List<Planning> findByPatient_id(Long patientId);
	
	@Query(value = "SELECT * FROM planning p "
			+ "JOIN planning_state ps ON (p.state_id = ps.id) "
			+ "JOIN patient pa ON (p.patient_id = pa.id) "
			+ "WHERE pa.id = ?1 "
			+ "AND (ps.name = ?2 OR ps.name = ?3)", nativeQuery = true)
	List<Planning>findByPatient_IdWithTwoStates(Long patientId, String statename1, String statename2);
	
	Optional<Planning> findById(Long id);
	Optional<Planning> findByDetail_HayUnoRepetidoSession_Results_Id(Long id);
	
	List<Planning> findByPatient_IdAndStartDateBeforeAndDueDateAfterAndState_IdNot(Long id, Date startDate, Date dueDate, Long stateId);
	
	List<Planning> findByState_nameOrState_name(String firstState, String secondState);
	
	@Query("select p from Planning p WHERE "
			+ "(TRANSLATE(LOWER(name),'áéíóú', 'aeiou') LIKE %?1% OR "
			+ "TRANSLATE(LOWER(p.patient.firstName || ' ' || "
			+ "p.patient.lastName),'áéíóú', 'aeiou') LIKE %?1%)"
			+ "ORDER BY id DESC")
	List<Planning> findFiltered(String planningName);
	
	@Query("select p from Planning p WHERE "
			+ "p.patient.id = ?2 AND "
			+ "(TRANSLATE(LOWER(name),'áéíóú', 'aeiou') LIKE %?1% OR "
			+ "TRANSLATE(LOWER(p.patient.firstName || ' ' || "
			+ "p.patient.lastName),'áéíóú', 'aeiou') LIKE %?1%)"
			+ "ORDER BY id DESC")
	List<Planning> findFiltered(String planningName, Long patientId);
	
	@Query(value = "Select p FROM Planning p JOIN p.detail pd WHERE ?1 IN pd")
	Optional<Planning> findByPlanningDetail(PlanningDetail pd);

	@Query(value="SELECT p.mgp from Planning p WHERE p.patient.id=?1 AND p.mgp IS NOT NULL")
	List<Integer> getPlanningsMGPs(Long patientId);
	
}
