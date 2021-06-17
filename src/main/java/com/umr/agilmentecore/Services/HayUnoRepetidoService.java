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
	
	/**
	 *  Obtiene todos los resultados de HayUnoRepetido
	 * @param page Contiene las opciones de paginación
	 * @return Una página de resultados
	 */
	public Page<HayUnoRepetido> getAll(Pageable page) {
		return repository.findAllByOrderByDateTimeDesc(page);
	}
	
	/**
	 * Guarda un resultado de Hay uno Repetido
	 * @param g Un resultado de HayUnoRepetido
	 * @return el resultado del juego guardado
	 */
	public HayUnoRepetido saveGame(HayUnoRepetido g) {
		
		return repository.save(g);
	}
	
	/**
	 *  Obtiene un resultado de HayUnoRepetido
	 * @param Long el id del juego específico
	 * @return Optional un resultado de un juego o nada.
	 */
	public Optional<HayUnoRepetido> getOne(Long id) {
		return repository.findById(id);
	}
	
}
      