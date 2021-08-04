package com.umr.agilmentecore.Persistence;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.HayUnoRepetidoResult;

@Repository
public interface HayUnoRepetidoResultRepository extends JpaRepository<HayUnoRepetidoResult, Long> {
	Page<HayUnoRepetidoResult> findAllByOrderByCompleteDatetimeDesc(Pageable page);
}
