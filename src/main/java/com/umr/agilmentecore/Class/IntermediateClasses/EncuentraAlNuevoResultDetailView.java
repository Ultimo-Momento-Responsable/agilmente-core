package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.umr.agilmentecore.Class.HayUnoRepetidoSession;
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
	private Long patient;
	private String game;
	@Transient
	private HayUnoRepetidoSession session;

	public EncuentraAlNuevoResultDetailView(Long id, Date completeDatetime, boolean canceled, int mistakes, int successes,
		float[] timeBetweenSuccesses, float totalTime, Long patient, String game, HayUnoRepetidoSession session) {
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

	public Long getPatient() {
		return patient;
	}

	public void setPatient(Long patient) {
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
}