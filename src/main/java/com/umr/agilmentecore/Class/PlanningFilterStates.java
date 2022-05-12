package com.umr.agilmentecore.Class;

import java.util.List;

public class PlanningFilterStates {
	private String search;
	private List<String> states;
	private Long patientId;
	
	public String getSearch() {
		return search;
	}
	public List<String> getStates() {
		return states;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
}
