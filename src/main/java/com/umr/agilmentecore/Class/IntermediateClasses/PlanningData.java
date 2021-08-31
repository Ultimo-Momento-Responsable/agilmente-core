package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PlanningData {
	private Long patientId;
	private String patientFirstName;
	private String patientLastName;
	private Long professionalId;
	private String professionalFirstName;
	private String professionalLastName;
	private String stateName;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date startDate;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dueDate;
	private List<GameData> games;
	private List<PlanningMobileData> planningList;
	private Long stateId;
	
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public void setProfessionalId(Long professionalId) {
		this.professionalId = professionalId;
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
	
	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public String getProfessionalFirstName() {
		return professionalFirstName;
	}

	public void setProfessionalFirstName(String professionalFirstName) {
		this.professionalFirstName = professionalFirstName;
	}

	public String getProfessionalLastName() {
		return professionalLastName;
	}

	public void setProfessionalLastName(String professionalLastName) {
		this.professionalLastName = professionalLastName;
	}

	public List<PlanningMobileData> getPlanningList() {
		return planningList;
	}

	public void setPlanningList(List<PlanningMobileData> planningList) {
		this.planningList = planningList;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public PlanningData(Long patientId, String patFirstName, String patLastName,
			Long professionalId, String profFirstName, String profLastName,
			String state, Date startDate, Date dueDate, List<PlanningMobileData> pl)
	{
			super();
			this.patientId = patientId;
			this.patientFirstName = patFirstName;
			this.patientLastName = patLastName;
			this.professionalId = professionalId;
			this.professionalFirstName = profFirstName;
			this.professionalLastName = profLastName;
			
			this.stateName = state;
			this.startDate = startDate;
			this.dueDate = dueDate;
			this.planningList = pl;
	}
	
	public PlanningData() {
	}
	
}
