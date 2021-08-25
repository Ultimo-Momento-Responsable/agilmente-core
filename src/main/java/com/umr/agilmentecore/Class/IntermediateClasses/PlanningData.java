package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PlanningData {
	private Long patientId;
	private Long professionalId;
	private Long stateId;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date startDate;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dueDate;
	private List<GameData> games;
	
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
}
