package com.umr.agilmentecore.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.Game;
import com.umr.agilmentecore.Persistence.GameRepository;

@Service
public class GameService {
	@Autowired
	private GameRepository repository;
	
	public Optional<Game> getOne(Integer id) {
		return this.repository.findById(id);
	}
}
