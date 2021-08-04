package com.umr.agilmentecore.Services;

import java.awt.print.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.HayUnoRepetidoResult;
import com.umr.agilmentecore.Persistence.HayUnoRepetidoResultRepository;

@Service
public class GameSessionResultService {
	@Autowired
	private HayUnoRepetidoResultRepository repository;
	
	public Page<HayUnoRepetidoResult> getAllResultsOrdered(Pageable page) {
		return this.repository.findAllByOrderByDateTimeDesc(page);
	}
}
