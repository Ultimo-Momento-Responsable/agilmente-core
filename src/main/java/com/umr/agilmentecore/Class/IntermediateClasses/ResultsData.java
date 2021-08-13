package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ResultsData {
	private Long id;
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date completeDatetime;
	private boolean canceled;
	private int mistakes;
	private int successes;
	private float[] timeBetweenSuccesses;
	private float totalTime;
	private String patient;
	private String game;
	
	public ResultsData(Long id, Date completeDatetime, boolean canceled, int mistakes, int successes, float[] timeBetweenSuccesses, float totalTime) {
		this.id = id;
		this.completeDatetime = completeDatetime;
		this.canceled = canceled;
		this.mistakes = mistakes;
		this.successes = successes;
		this.timeBetweenSuccesses = timeBetweenSuccesses;
		this.totalTime = totalTime;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}
	
	
}
