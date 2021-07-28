package com.umr.agilmentecore.Persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.HayUnoRepetidoSession;

@Repository
public interface HayUnoRepetidoSessionRepository extends JpaRepository<HayUnoRepetidoSession, Long>{
	
//	Page<HayUnoRepetidoSession> findAllByOrderByDateTimeDesc(Pageable page);
//	Optional<HayUnoRepetidoSession> findById(Long id);
}