package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PlanningOverview {
	private Long planningId;
	private String planningName;
	private String patientName;
	private String professionalName;
	private String stateName;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date startDate;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dueDate;
	
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public void setProfessionalName(String professionalName) {
		this.professionalName = professionalName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public void setPlanningId(Long planningId) {
		this.planningId = planningId;
	}

	public String getProfessionalName() {
		return this.professionalName;
	}
	
	public String getStateName() {
		return this.stateName;
	}
	
	public String getPatientName() {
		return this.patientName;
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	
	public Date getDueDate() {
		return this.dueDate;
	}
	
	public Long getPlanningId() {
		return planningId;
	}

	public String getPlanningName() {
		return planningName;
	}

	public void setPlanningName(String planningName) {
		this.planningName = planningName;
	}

}
