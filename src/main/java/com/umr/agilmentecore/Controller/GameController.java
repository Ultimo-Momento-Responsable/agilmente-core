package com.umr.agilmentecore.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umr.agilmentecore.Class.Game;
import com.umr.agilmentecore.Services.GameService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/game")
public class GameController {
	@Autowired
	private GameService service;
	
	/**
	 *  Obtiene todos los resultados de Game.
	 * @return Una lista de resultados.
	 */
	@GetMapping
	public List<Game> getAll() {
		return service.getAll();
	}
	
	/**
	 * Obtiene los juegos que contengan alguno o todos los dominios cognitivos seleccionados
	 * @param cognitiveDomains Dominios Cognitivos seleccionados.
	 * @return Lista de juegos.
	 */
	@GetMapping(params = {"cognitiveDomains"})
	public List<Game> getGamesByCognitiveDomains(String[] cognitiveDomains) {
		ArrayList<String> cognitiveDomainsValue = new ArrayList<String>();
		Collections.addAll(cognitiveDomainsValue, cognitiveDomains);
		
		return service.getGamesByCognitiveDomains(cognitiveDomainsValue);
	}
	
	/**
	 * Obtiene un Juego.
	 * @param Long el id del juego específico.
	 * @return Optional un juego o nada.
	 */
	@GetMapping(value = "/{id}")
	public Optional<Game> getOne(@PathVariable(name = "id") int id) {
		return service.getOne(id);
	}
}
