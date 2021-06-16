package com.umr.agilmentecore.Persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.HayUnoRepetido;

import org.springframework.data.domain.Page;

@Repository
public interface HayUnoRepetidoRepository extends JpaRepository<HayUnoRepetido, Integer>{
	
	Page<HayUnoRepetido> findAll(Pageable page);
	
}