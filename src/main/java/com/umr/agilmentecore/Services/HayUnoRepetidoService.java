package com.umr.agilmentecore.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.HayUnoRepetido;
import com.umr.agilmentecore.Persistence.HayUnoRepetidoRepository;


@Service
public class HayUnoRepetidoService {
	
	@Autowired
	private HayUnoRepetidoRepository repository;
	
	public Page<HayUnoRepetido> getAll(Pageable page) {
		return repository.findAllByOrderByDateTimeDesc(page);
	}
	
	public HayUnoRepetido saveGame(HayUnoRepetido g) {
		
		return repository.save(g);
	}

	public Optional<HayUnoRepetido> getOne(Long id) {
		return repository.findById(id);
	}
	
}
      