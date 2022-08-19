package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PlanningWithSessions {
	private Long planningId;
	private int totalGames;
	private int gamesPlayed;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dueDate;
	private List<PlanningMobileData> planningList = new ArrayList<PlanningMobileData>();
	private boolean unlimited;
	
	public PlanningWithSessions(Long planningId, int totalGames, int gamesPlayed, Date dueDate, boolean unlimited, List<PlanningMobileData> planningList) {
		this.planningId = planningId;
		this.totalGames = totalGames;
		this.dueDate = dueDate;
		this.gamesPlayed = gamesPlayed;
		this.planningList = planningList;
		this.unlimited = unlimited;
	}

	public Long getPlanningId() {
		return planningId;
	}

	public void setPlanningId(Long planningId) {
		this.planningId = planningId;
	}

	public int getTotalGames() {
		return totalGames;
	}

	public void setTotalGames(int totalGames) {
		this.totalGames = totalGames;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	
	public List<PlanningMobileData> getPlanningList() {
		return planningList;
	}

	public void setPlanningList(List<PlanningMobileData> planningList) {
		this.planningList = planningList;
	}

	public boolean isUnlimited() {
		return unlimited;
	}

	public void setUnlimited(boolean unlimited) {
		this.unlimited = unlimited;
	}
}
