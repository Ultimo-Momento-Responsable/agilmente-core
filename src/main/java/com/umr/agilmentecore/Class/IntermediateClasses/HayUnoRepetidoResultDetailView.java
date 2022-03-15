package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.umr.agilmentecore.Class.HayUnoRepetidoSession;
import com.umr.agilmentecore.Interfaces.IParam;

public class HayUnoRepetidoResultDetailView {
	private Long id;
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date completeDatetime;
	private boolean canceled;
	private int mistakes;
	private int successes;
	private int productivity;
	private float[] timeBetweenSuccesses;
	private float totalTime;
	private String patient;
	private String game;
	private Long hayUnoRepetidoSessionId;
	private int score;
	@Transient
	private HayUnoRepetidoSession session;

	public HayUnoRepetidoResultDetailView(Long id, Date completeDatetime, boolean canceled, int mistakes, int successes, int productivity,
		float[] timeBetweenSuccesses, float totalTime, int score, String patient, String game, HayUnoRepetidoSession session) {
		super();
		this.id = id;
		this.completeDatetime = completeDatetime;
		this.canceled = canceled;
		this.mistakes = mistakes;
		this.successes = successes;
		this.productivity = productivity;
		this.timeBetweenSuccesses = timeBetweenSuccesses;
		this.totalTime = totalTime;
		this.patient = patient;
		this.game = game;
		this.session = session;
		this.score = score;
	}
	
	@JsonCreator
	public HayUnoRepetidoResultDetailView(
		@JsonProperty("completeDatetime") Date completeDatetime, @JsonProperty("canceled") boolean canceled, 
		@JsonProperty("mistakes") int mistakes, @JsonProperty("successes") int successes, @JsonProperty("productivity") int productivity,
		@JsonProperty("timeBetweenSuccesses") float[] timeBetweenSuccesses, @JsonProperty("totalTime") float totalTime, 
		@JsonProperty("score") int score,
		@JsonProperty("game") String game, @JsonProperty("hayUnoRepetidoSessionId") Long hayUnoRepetidoSessionId) {
		
		this.completeDatetime = completeDatetime;
		this.canceled = canceled;
		this.mistakes = mistakes;
		this.successes = successes;
		this.productivity = productivity;
		this.timeBetweenSuccesses = timeBetweenSuccesses;
		this.totalTime = totalTime;
		this.game = game;
		this.hayUnoRepetidoSessionId = hayUnoRepetidoSessionId;
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

	public Long getHayUnoRepetidoSessionId() {
		return hayUnoRepetidoSessionId;
	}

	public int getProductivity() {
		return productivity;
	}

	public void setProductivity(int productivity) {
		this.productivity = productivity;
	}
}