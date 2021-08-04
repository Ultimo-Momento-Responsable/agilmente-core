package com.umr.agilmentecore.Persistence;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.HayUnoRepetidoResult;

@Repository
public interface HayUnoRepetidoResultRepository extends JpaRepository<HayUnoRepetidoResult, Long> {
	Page<HayUnoRepetidoResult> findAllByOrderByDateTimeDesc(Pageable page);
}
