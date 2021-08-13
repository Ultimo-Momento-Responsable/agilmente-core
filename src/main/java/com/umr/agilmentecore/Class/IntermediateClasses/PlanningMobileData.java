package com.umr.agilmentecore.Class.IntermediateClasses;

public class PlanningMobileData {
	private String game;
	private Integer numberOfSession;
	
	public PlanningMobileData(String game, int numberOfSession) {
		this.setGame(game);
		this.setNumberOfSession(numberOfSession);
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
}
