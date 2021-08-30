package com.umr.agilmentecore.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.EncuentraAlNuevoSession;

@Repository
public interface EncuentraAlNuevoSessionRepository extends JpaRepository<EncuentraAlNuevoSession, Long>{
	
}