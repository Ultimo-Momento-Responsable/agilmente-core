package com.umr.agilmentecore.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.HayUnoRepetidoSession;

@Repository
public interface HayUnoRepetidoSessionRepository extends JpaRepository<HayUnoRepetidoSession, Long>{
	
}