package com.umr.agilmentecore.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umr.agilmentecore.Class.IntermediateClasses.ResultsData;
import com.umr.agilmentecore.Services.GameSessionResultService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/results")
public class GameSessionResultController {
	@Autowired
	private GameSessionResultService service;
	
	/**
	 * Obtiene la lista de resultados completa.
	 * Devuelve una lista de ResultsData
	 */
	@GetMapping
	public List<ResultsData> getAllResultsOrdered() {
		return this.service.createResultList();
	}
	
}
