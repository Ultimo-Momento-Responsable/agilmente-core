package com.umr.agilmentecore.Persistence;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.Planning;

@Repository
public interface PlanningRepository extends JpaRepository<Planning, Long> {
	List<Planning> findByPatient_idAndState_name(Long patientId, String name);
	
	Optional<Planning> findByDetail_HayUnoRepetidoSession_Results_Id(Long id);
	
	List<Planning> findByStartDateBeforeAndDueDateAfter(Date startDate, Date dueDate);
	
	List<Planning> findByState_nameOrState_name(String firstState, String secondState);
}
