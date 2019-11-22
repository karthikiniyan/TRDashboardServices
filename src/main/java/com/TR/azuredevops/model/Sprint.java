package com.TR.azuredevops.model;
import java.util.ArrayList;
import java.util.Date;

public class Sprint {

	private String id;
	private String name;
	private String url;

	private Date startDate;
	private Date endDate;

	private ArrayList<WorkItem> workItems = new ArrayList<WorkItem>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ArrayList<WorkItem> getWorkItems() {
		return workItems;
	}

	public void setWorkItems(ArrayList<WorkItem> workItems) {
		this.workItems = workItems;
	}

}
