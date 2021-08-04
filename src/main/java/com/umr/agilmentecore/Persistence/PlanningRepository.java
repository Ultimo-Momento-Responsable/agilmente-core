package com.umr.agilmentecore.Persistence;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.Planning;

@Repository
public interface PlanningRepository extends JpaRepository<Planning, Long> {
	List<Planning> findByPatient_idAndStartDateBeforeAndDueDateAfter(Long patientId, Date startDate, Date dueDate);
}