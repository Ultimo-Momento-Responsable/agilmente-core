package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.List;

import com.umr.agilmentecore.Class.MemorillaResult;

public class PatientResultsMemorillaView {
	private List<MemorillaResult> results;
	private String gameName = "Memorilla";

	
	public PatientResultsMemorillaView(List<MemorillaResult> results) {
		super();
		this.results = results;
	}
	
	public List<MemorillaResult> getResults() {
		return results;
	}
	
	public void setResults(List<MemorillaResult> results) {
		this.results = results;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
}
