package com.TR.azuredevops.model;
import java.sql.Timestamp;

public class DAGWorkItem {

	private String issueId = null;
	private String project = null;
	private String issueType = null;
	private String priority = null;
	private String reporter = null;
	private String assignee = null;
	private String resolution = null;
	private String label = null;
	private String status = null;
	private String bussinessUnit = null;
	private String storypoints = null;
	private String epicLink = null;
	private String sprint = null;
	private String sprinthistory = null;
	private String timeorignalEstimate = null;
	private String timespent = null;
	private Timestamp created;
	private Timestamp updated;
	private Timestamp duedate;
	private Timestamp sprintstartdate;
	private Timestamp sprintenddate;

	public String getIssueId() {
		return issueId;
	}

	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBussinessUnit() {
		return bussinessUnit;
	}

	public void setBussinessUnit(String bussinessUnit) {
		this.bussinessUnit = bussinessUnit;
	}

	public String getStorypoints() {
		return storypoints;
	}

	public void setStorypoints(String storypoints) {
		this.storypoints = storypoints;
	}

	public String getEpicLink() {
		return epicLink;
	}

	public void setEpicLink(String epicLink) {
		this.epicLink = epicLink;
	}

	public String getSprint() {
		return sprint;
	}

	public void setSprint(String sprint) {
		this.sprint = sprint;
	}

	public String getSprinthistory() {
		return sprinthistory;
	}

	public void setSprinthistory(String sprinthistory) {
		this.sprinthistory = sprinthistory;
	}

	public String getTimeorignalEstimate() {
		return timeorignalEstimate;
	}

	public void setTimeorignalEstimate(String timeorignalEstimate) {
		this.timeorignalEstimate = timeorignalEstimate;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Timestamp getDuedate() {
		return duedate;
	}

	public void setDuedate(Timestamp duedate) {
		this.duedate = duedate;
	}

	public Timestamp getSprintstartdate() {
		return sprintstartdate;
	}

	public void setSprintstartdate(Timestamp sprintstartdate) {
		this.sprintstartdate = sprintstartdate;
	}

	public Timestamp getSprintenddate() {
		return sprintenddate;
	}

	public void setSprintenddate(Timestamp sprintenddate) {
		this.sprintenddate = sprintenddate;
	}

	public String getTimespent() {
		return timespent;
	}

	public void setTimespent(String timespent) {
		this.timespent = timespent;
	}

}
