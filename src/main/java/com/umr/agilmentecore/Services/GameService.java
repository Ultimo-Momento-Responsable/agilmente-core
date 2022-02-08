package com.umr.agilmentecore.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.CognitiveDomain;
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
		return this.repository.findByOrderByName();
	}

	/**
	 * Obtiene un juego a partir del id.
	 * @param id Id del juego.
	 * @return Juego.
	 */
	public Optional<Game> getOne(Integer id) {
		return this.repository.findById(id);
	}

	/**
	 * Obtiene una lista con los juegos filtrados ordenandolos por la mayor cantidad de coincidencias.
	 * @param cognitiveDomains Lista de dominios cognitivos
	 * @return Devuelve una lista ordenada de juegos.
	 */
	public List<Game> getGamesByCognitiveDomains(List<String> cognitiveDomains) {
		List<Game> allGames = this.repository.findByOrderByName();
		List<Game> gamesFiltered = new ArrayList<Game>();
		List<Integer> coincidencesList = new ArrayList<Integer>();
		for (Game g : allGames) {
			boolean gameWithCD = false;
			int coincidences = 0;
			
			for (int i = 0; i < cognitiveDomains.size(); i++) {
				for (CognitiveDomain cd : g.getCognitiveDomain()) {
					if (cognitiveDomains.get(i).equals(cd.getName())) {
						gameWithCD = true;
						coincidences++;
					}
				}
			}
			if (gameWithCD) {
				gamesFiltered.add(g);
				coincidencesList.add(coincidences);
			}
		
		}
		for (int i=1; i < coincidencesList.size(); i++) {
		    int aux = coincidencesList.get(i);
		    Game auxGame = gamesFiltered.get(i);
		    int j;
		    for (j=i-1; j >= 0 && coincidencesList.get(j) < aux; j--){
		    	coincidencesList.set(j+1, coincidencesList.get(j));
		    	gamesFiltered.set(j+1, gamesFiltered.get(j));
		    }
		    coincidencesList.set(j+1, aux);
		    gamesFiltered.set(j+1, auxGame);
		}
		return gamesFiltered;
	}


}
