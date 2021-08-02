package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.List;

public class GameData {
	private Integer gameId;
	private Integer maxNumberOfSessions;
	private List<ParamData> params;
	
	public Integer getGameId() {
		return this.gameId;
	}
	
	public Integer getMaxNumberOfSessions() {
		return this.maxNumberOfSessions;
	}
	
	public List<ParamData> getParams() {
		return this.params;
	}
}
