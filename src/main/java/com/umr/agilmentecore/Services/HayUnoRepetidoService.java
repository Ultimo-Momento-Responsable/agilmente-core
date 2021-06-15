package com.umr.agilmentecore.Services;

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
	
	public Page<HayUnoRepetido> getAll(Pageable pagina) {
		return repository.findAll(pagina);
	}
	
	public HayUnoRepetido saveGame(HayUnoRepetido j) {
		
		return repository.saveAndFlush(j);
	}
}
      