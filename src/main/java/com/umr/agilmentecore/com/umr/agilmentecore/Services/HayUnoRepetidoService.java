package com.umr.agilmentecore.com.umr.agilmentecore.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.com.umr.agilmentecore.Class.HayUnoRepetido;
import com.umr.agilmentecore.com.umr.agilmentecore.Persistence.HayUnoRepetidoRepository;


@Service
public class HayUnoRepetidoService {
	
	@Autowired
	private HayUnoRepetidoRepository repositorio;
	
	public Page<HayUnoRepetido> listarTodos(Pageable pagina) {
		return repositorio.findAll(pagina);
	}
	
	public HayUnoRepetido guardar(HayUnoRepetido j) {
		
		return repositorio.save(j);
	}
}
