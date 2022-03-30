package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.umr.agilmentecore.Class.MemorillaSession;
import com.umr.agilmentecore.Interfaces.IParam;

public class MemorillaResultDetailView {
	private Long id;
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date completeDatetime;
	private boolean canceled;
	private int[] mistakesPerLevel;
	private int[] successesPerLevel;
	private int streak;
	private float[] timePerLevel;
	private float totalTime;
	private String patient;
	private String game;
	private Long memorillaSessionId;
	private int score;
	@Transient
	private MemorillaSession session;

	public MemorillaResultDetailView(Long id, Date completeDatetime, boolean canceled, int[] mistakesPerLevel, int[] successesPerLevel,
		int streak, float[] timePerLevel, float totalTime, int score, String patient, String game, MemorillaSession session) {
		super();
		this.id = id;
		this.completeDatetime = completeDatetime;
		this.canceled = canceled;
		this.mistakesPerLevel = mistakesPerLevel;
		this.successesPerLevel = successesPerLevel;
		this.streak = streak;
		this.timePerLevel = timePerLevel;
		this.totalTime = totalTime;
		this.patient = patient;
		this.game = game;
		this.session = session;
		this.score = score;
	}
	
	@JsonCreator
	public MemorillaResultDetailView(
		@JsonProperty("completeDatetime") Date completeDatetime, @JsonProperty("canceled") boolean canceled, 
		@JsonProperty("mistakesPerLevel") int[] mistakesPerLevel, @JsonProperty("successesPerLevel") int[] successesPerLevel,
		@JsonProperty("streak") int streak, @JsonProperty("timePerLevel") float[] timePerLevel, @JsonProperty("totalTime") float totalTime, 
		@JsonProperty("score") int score, @JsonProperty("game") String game, @JsonProperty("memorillaSessionId") Long memorillaSessionId) {
		
		this.completeDatetime = completeDatetime;
		this.canceled = canceled;
		this.mistakesPerLevel = mistakesPerLevel;
		this.successesPerLevel = successesPerLevel;
		this.setStreak(streak);
		this.timePerLevel = timePerLevel;
		this.totalTime = totalTime;
		this.game = game;
		this.memorillaSessionId = memorillaSessionId;
		this.score = score;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCompleteDatetime() {
		return completeDatetime;
	}

	public void setCompleteDatetime(Date completeDatetime) {
		this.completeDatetime = completeDatetime;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
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

	public float[] getTimePerLevel() {
		return timePerLevel;
	}

	public void setTimePerLevel(float[] timePerLevel) {
		this.timePerLevel = timePerLevel;
	}

	public float getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(float totalTime) {
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<IParam> getParams() {
		return this.session.getSettedParams();
	}

	public Long getMemorillaSessionId() {
		return memorillaSessionId;
	}

	public int getStreak() {
		return streak;
	}

	public void setStreak(int streak) {
		this.streak = streak;
	}
}