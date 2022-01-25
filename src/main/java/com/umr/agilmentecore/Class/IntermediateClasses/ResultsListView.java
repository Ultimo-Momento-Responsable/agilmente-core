package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ResultsListView {
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
	private int score;
	
	public ResultsListView(Long id, Date completeDatetime, boolean canceled, int mistakes, int successes, float[] timeBetweenSuccesses, float totalTime, int score, String patient, String game) {
		this.setId(id);
		this.completeDatetime = completeDatetime;
		this.setCanceled(canceled);
		this.setMistakes(mistakes);
		this.setSuccesses(successes);
		this.setTimeBetweenSuccesses(timeBetweenSuccesses);
		this.setTotalTime(totalTime);
		this.patient = patient;
		this.game = game;
		this.setScore(score);
	}

	public Date getCompleteDatetime() {
		return completeDatetime;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public int getMistakes() {
		return mistakes;
	}

	public void setMistakes(int mistakes) {
		this.mistakes = mistakes;
	}

	public int getSuccesses() {
		return successes;
	}

	public void setSuccesses(int successes) {
		this.successes = successes;
	}

	public float[] getTimeBetweenSuccesses() {
		return timeBetweenSuccesses;
	}

	public void setTimeBetweenSuccesses(float[] timeBetweenSuccesses) {
		this.timeBetweenSuccesses = timeBetweenSuccesses;
	}

	public float getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(float totalTime) {
		this.totalTime = totalTime;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
