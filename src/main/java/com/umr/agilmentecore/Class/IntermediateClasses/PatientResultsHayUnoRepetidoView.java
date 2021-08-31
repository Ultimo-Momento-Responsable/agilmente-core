package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.List;

import com.umr.agilmentecore.Class.HayUnoRepetidoResult;

public class PatientResultsHayUnoRepetidoView {
	private List<HayUnoRepetidoResult> results;
	
	public PatientResultsHayUnoRepetidoView(List<HayUnoRepetidoResult> results) {
		super();
		this.results = results;
	}
	
	public List<HayUnoRepetidoResult> getResults() {
		return results;
	}
	
	public void setResults(List<HayUnoRepetidoResult> results) {
		this.results = results;
	}
}
