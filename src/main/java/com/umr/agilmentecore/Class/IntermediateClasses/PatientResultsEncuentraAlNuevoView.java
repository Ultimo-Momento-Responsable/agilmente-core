package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.List;

import com.umr.agilmentecore.Class.EncuentraAlNuevoResult;

public class PatientResultsEncuentraAlNuevoView {
	private List<EncuentraAlNuevoResult> results;
	
	public PatientResultsEncuentraAlNuevoView(List<EncuentraAlNuevoResult> results) {
		super();
		this.results = results;
	}
	
	public List<EncuentraAlNuevoResult> getResults() {
		return results;
	}
	
	public void setResults(List<EncuentraAlNuevoResult> results) {
		this.results = results;
	}
}
