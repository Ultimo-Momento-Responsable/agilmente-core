package com.umr.agilmentecore.Persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
	List<Game> findByOrderByName();
	
}
