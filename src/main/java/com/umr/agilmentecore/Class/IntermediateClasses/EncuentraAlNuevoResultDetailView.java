package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.umr.agilmentecore.Class.EncuentraAlNuevoSession;
import com.umr.agilmentecore.Interfaces.IParam;

public class EncuentraAlNuevoResultDetailView {
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
	private Long encuentraAlNuevoSessionId;
	@Transient
	private EncuentraAlNuevoSession session;

	public EncuentraAlNuevoResultDetailView(Long id, Date completeDatetime, boolean canceled, int mistakes, int successes,
		float[] timeBetweenSuccesses, float totalTime, String patient, String game, EncuentraAlNuevoSession session) {
		super();
		this.id = id;
		this.completeDatetime = completeDatetime;
		this.canceled = canceled;
		this.mistakes = mistakes;
		this.successes = successes;
		this.timeBetweenSuccesses = timeBetweenSuccesses;
		this.totalTime = totalTime;
		this.patient = patient;
		this.game = game;
		this.session = session;
	}
	
	@JsonCreator
	public EncuentraAlNuevoResultDetailView(@JsonProperty("completeDatetime") Date completeDatetime, @JsonProperty("canceled") boolean canceled, 
			@JsonProperty("mistakes") int mistakes, @JsonProperty("successes") int successes,
			@JsonProperty("timeBetweenSuccesses") float[] timeBetweenSuccesses, @JsonProperty("totalTime") float totalTime, 
			@JsonProperty("game") String game, @JsonProperty("encuentraAlNuevoSessionId") Long encuentraAlNuevoSessionId) {
			this.completeDatetime = completeDatetime;
			this.canceled = canceled;
			this.mistakes = mistakes;
			this.successes = successes;
			this.timeBetweenSuccesses = timeBetweenSuccesses;
			this.totalTime = totalTime;
			this.game = game;
			this.encuentraAlNuevoSessionId = encuentraAlNuevoSessionId;
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

	public List<IParam> getParams() {
		return this.session.getSettedParams();
	}

	public Long getEncuentraAlNuevoSessionId() {
		return encuentraAlNuevoSessionId;
	}

	public void setEncuentraAlNuevoSessionId(Long encuentraAlNuevoSessionId) {
		this.encuentraAlNuevoSessionId = encuentraAlNuevoSessionId;
	}
}