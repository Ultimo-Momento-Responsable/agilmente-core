package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.List;

public class PlanningWithSessionsList {
	public List<PlanningWithSessions> planningList;

	public PlanningWithSessionsList(List<PlanningWithSessions> planningList) {
		this.planningList = planningList;
	}
	public List<PlanningWithSessions> getPlanningList() {
		return planningList;
	}

	public void setPlanningList(List<PlanningWithSessions> planningList) {
		this.planningList = planningList;
	}
	
}
