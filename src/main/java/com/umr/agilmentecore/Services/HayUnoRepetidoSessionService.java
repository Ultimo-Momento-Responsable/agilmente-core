package com.umr.agilmentecore.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.HayUnoRepetidoSession;
import com.umr.agilmentecore.Persistence.HayUnoRepetidoSessionRepository;

@Service
public class HayUnoRepetidoSessionService {
	@Autowired
	private HayUnoRepetidoSessionRepository repository;
	
	/**
	 * Guarda un resultado de Hay uno Repetido
	 * @param g Un resultado de HayUnoRepetido
	 * @return el resultado del juego guardado
	 */
	public HayUnoRepetidoSession save(HayUnoRepetidoSession g) {
		return repository.save(g);
	}
	
	/**
	 * Obtiene una sesión de HayUnoRepetido.
	 * @param Long el id del juego específico
	 * @return Optional una sesión.
	 */
	public Optional<HayUnoRepetidoSession> getOne(Long id) {
		return repository.findById(id);
	}
	
}
      