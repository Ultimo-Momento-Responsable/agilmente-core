package com.umr.agilmentecore.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.PlanningState;

@Repository
public interface PlanningStateRepository extends JpaRepository<PlanningState, Long> {
	PlanningState findByName(String name);
}
