package com.TR.TRDashboard;

import java.util.ArrayList;

public class IssueTypeModel {
	
	private String name = null;
	
	private String assignee = null;
	
	private String priority = null;
	
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	private int Count =0;
	
	private ArrayList<ResolutionModel> resolution;
	
	public ArrayList<ResolutionModel> getResolution() {
		return resolution;
	}
	public void setResolution(ArrayList<ResolutionModel> resolution) {
		this.resolution = resolution;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	

}
