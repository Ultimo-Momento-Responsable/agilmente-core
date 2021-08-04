package com.umr.agilmentecore.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.Game;
import com.umr.agilmentecore.Persistence.GameRepository;

@Service
public class GameService {
	@Autowired
	private GameRepository repository;
	
	/**
	 * Obtiene una lista con todos los juegos.
	 * @return Lista de juegos.
	 */
	public List<Game> getAll() {
		return this.repository.findAll();
	}

	/**
	 * Obtiene un juego a partir del id.
	 * @param id Id del juego.
	 * @return Juego.
	 */
	public Optional<Game> getOne(Integer id) {
		return this.repository.findById(id);
	}
}
