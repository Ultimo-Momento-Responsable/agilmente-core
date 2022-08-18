package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ResultsListView {
	private Long id;
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date completeDatetime;
	private boolean canceled;
	private int mistakes;
	private int[] mistakesPerLevel;
	private int successes;
	private int[] successesPerLevel;
	private int streak;
	private float[] timeBetweenSuccesses;
	private float[] timePerLevel;
	private float totalTime;
	private String patient;
	private String game;
	private int score;
	private int mgp;
	
	public ResultsListView(Long id, Date completeDatetime, boolean canceled, int mistakes, int successes, float[] timeBetweenSuccesses, float totalTime, int score, int mgp, String patient, String game) {
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
		this.setMgp(mgp);
	}

	public ResultsListView(Long id, Date completeDatetime, boolean canceled, int[] mistakesPerLevel, int[] successesPerLevel, int streak, float[] timePerLevel, float totalTime, int score, int mgp, String patient, String game) {
		this.setId(id);
		this.completeDatetime = completeDatetime;
		this.setCanceled(canceled);
		this.setMistakesPerLevel(mistakesPerLevel);
		this.setSuccessesPerLevel(successesPerLevel);
		this.setStreak(streak);
		this.setTimePerLevel(timePerLevel);
		this.setTotalTime(totalTime);
		this.patient = patient;
		this.game = game;
		this.setScore(score);
		this.setMgp(mgp);
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

	public int[] getMistakesPerLevel() {
		return mistakesPerLevel;
	}

	public void setMistakesPerLevel(int[] mistakesPerLevel) {
		this.mistakesPerLevel = mistakesPerLevel;
	}

	public int[] getSuccessesPerLevel() {
		return successesPerLevel;
	}

	public void setSuccessesPerLevel(int[] successesPerLevel) {
		this.successesPerLevel = successesPerLevel;
	}

	public int getStreak() {
		return streak;
	}

	public void setStreak(int streak) {
		this.streak = streak;
	}

	public float[] getTimePerLevel() {
		return timePerLevel;
	}

	public void setTimePerLevel(float[] timePerLevel) {
		this.timePerLevel = timePerLevel;
	}

	public void setCompleteDatetime(Date completeDatetime) {
		this.completeDatetime = completeDatetime;
	}

	public int getMgp() {
		return mgp;
	}

	public void setMgp(int mgp) {
		this.mgp = mgp;
	}

}
