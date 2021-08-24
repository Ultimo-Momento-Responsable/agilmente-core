package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PlanningOverview {
	private Long patientId;
	private Long professionalId;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date startDate;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dueDate;
	
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

}
