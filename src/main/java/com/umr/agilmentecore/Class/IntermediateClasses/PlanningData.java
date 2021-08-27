package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.umr.agilmentecore.Class.HayUnoRepetidoSession;

public class PlanningData {
	private Long patientId;
	private Long professionalId;
	private Long stateId;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date startDate;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dueDate;
	private List<GameData> games;
	
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public void setProfessionalId(Long professionalId) {
		this.professionalId = professionalId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setGames(List<GameData> games) {
		this.games = games;
	}

	public Long getProfessionalId() {
		return this.professionalId;
	}
	
	public Long getPatientId() {
		return this.patientId;
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	
	public Date getDueDate() {
		return this.dueDate;
	}
	
	public List<GameData> getGames() {
		return this.games;
	}

	public Long getStateId() {
		return stateId;
	}
	
	public PlanningData(Long patientId, Long professionalId, Long stateId, Date startDate, Date dueDate)
	{
			super();
			this.patientId = patientId;
			this.professionalId = professionalId;
			this.stateId = stateId;
			this.startDate = startDate;
			this.dueDate = dueDate;
	}
	
}
