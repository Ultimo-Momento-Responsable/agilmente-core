package com.umr.agilmentecore.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.PlanningDetail;

@Repository
public interface PlanningDetailRepository extends JpaRepository<PlanningDetail, Long> {
	PlanningDetail findByHayUnoRepetidoSession_id(Long id);
	PlanningDetail findByEncuentraAlNuevoSession_id(Long id);
	
}
