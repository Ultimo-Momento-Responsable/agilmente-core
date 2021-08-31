package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.List;

import com.umr.agilmentecore.Interfaces.IParam;


public class PlanningMobileData {
	private Long gameSessionId;
	private String game;
	private Integer numberOfSession;
	private List<IParam> parameters;
	
	public PlanningMobileData(String game, int numberOfSession, List<IParam> parameters) {
		this.setGame(game);
		this.setNumberOfSession(numberOfSession);
		this.parameters = parameters;
	}
	
	public PlanningMobileData(Long gameSessionId, String game, int numberOfSession, List<IParam> parameters) {
		this.setGameSessionId(gameSessionId);
		this.setGame(game);
		this.setNumberOfSession(numberOfSession);
		this.parameters = parameters;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public int getNumberOfSession() {
		return numberOfSession;
	}

	public void setNumberOfSession(int numberOfSession) {
		this.numberOfSession = numberOfSession;
	}

	public List<IParam> getParameters() {
		return parameters;
	}

	public void setParameters(List<IParam> params) {
		this.parameters = params;
	}

	public Long getGameSessionId() {
		return gameSessionId;
	}

	public void setGameSessionId(Long gameSessionId) {
		this.gameSessionId = gameSessionId;
	}

}
