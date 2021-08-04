package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.Map;

public class GameData {
	private Integer gameId;
	private Integer maxNumberOfSessions;
	private Map<String, String> params;
	
	public Integer getGameId() {
		return this.gameId;
	}
	
	public Integer getMaxNumberOfSessions() {
		return this.maxNumberOfSessions;
	}
	
	public Map<String, String> getParams() {
		return this.params;
	}
}
