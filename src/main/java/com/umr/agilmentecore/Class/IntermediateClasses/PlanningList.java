package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.ArrayList;
import java.util.List;

public class PlanningList {
	private List<PlanningMobileData> planningList = new ArrayList<PlanningMobileData>();

	public List<PlanningMobileData> getPlanningList() {
		return planningList;
	}

	public void setPlanningList(List<PlanningMobileData> planningList) {
		this.planningList = planningList;
	}
	public PlanningList(List<PlanningMobileData> planningList) {
		this.planningList = planningList;
	}
}
