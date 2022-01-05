package com.umr.agilmentecore.Persistence;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.Planning;

@Repository
public interface PlanningRepository extends JpaRepository<Planning, Long> {
	List<Planning> findByPatient_idAndState_name(Long patientId, String name);
	
	@Query(value = "SELECT * FROM planning p "
			+ "JOIN planning_state ps ON (p.state_id = ps.id) "
			+ "JOIN patient pa ON (p.patient_id = pa.id) "
			+ "WHERE pa.id = ?1 "
			+ "AND (ps.name = ?2 OR ps.name = ?3)", nativeQuery = true)
	List<Planning>findByPatient_IdWithTwoStates(Long patientId, String statename1, String statename2);
	
	Optional<Planning> findById(Long id);
	Optional<Planning> findByDetail_HayUnoRepetidoSession_Results_Id(Long id);
	
	List<Planning> findByStartDateBeforeAndDueDateAfter(Date startDate, Date dueDate);
	
	List<Planning> findByState_nameOrState_name(String firstState, String secondState);
	
	@Query("select p from Planning p WHERE TRANSLATE(LOWER(name),'áéíóú', 'aeiou') LIKE %?1% OR TRANSLATE(LOWER(p.patient.firstName || ' ' "
			+ "|| p.patient.lastName),'áéíóú', 'aeiou') like %?1% AND (p.state.name='Pendiente' OR p.state.name='Vigente')")
	List<Planning> findByNameContainsOrPatient_FirstNameLastNameContains(String planningName);

}
