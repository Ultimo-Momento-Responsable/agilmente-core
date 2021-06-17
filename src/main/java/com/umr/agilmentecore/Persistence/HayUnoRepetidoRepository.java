package com.umr.agilmentecore.Persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.HayUnoRepetido;

@Repository
public interface HayUnoRepetidoRepository extends JpaRepository<HayUnoRepetido, Long>{
	
	Page<HayUnoRepetido> findAllByOrderByDateTimeDesc(Pageable page);
	Optional<HayUnoRepetido> findById(Long id);
}