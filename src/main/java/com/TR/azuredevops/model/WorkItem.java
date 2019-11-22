package com.TR.azuredevops.model;

import java.util.Date;

public class WorkItem {

	private Long id;

	private Double remainingWork;
	private Double effort;
	private Double originalEstimate;

	private String projectName;
	private String projectTeamName;
	private String sprintName;

	private String url;
	private String itemType;
	private String status;
	private String title;

	private String assignedTo;
	private String createdBy;
	private String changedBy;
	private String closedBy;
	private String severity;

	private Date createdDate;
	private Date updatedDate;
	private Date closedDate;

	private Date sprintStartDate;
	private Date sprintEndDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	public String getClosedBy() {
		return closedBy;
	}

	public void setClosedBy(String closedBy) {
		this.closedBy = closedBy;
	}

	public Double getRemainingWork() {
		return remainingWork;
	}

	public void setRemainingWork(Double remainingWork) {
		this.remainingWork = remainingWork;
	}

	public Double getEffort() {
		return effort;
	}

	public void setEffort(Double effort) {
		this.effort = effort;
	}

	public Double getOriginalEstimate() {
		return originalEstimate;
	}

	public void setOriginalEstimate(Double originalEstimate) {
		this.originalEstimate = originalEstimate;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectTeamName() {
		return projectTeamName;
	}

	public void setProjectTeamName(String projectTeamName) {
		this.projectTeamName = projectTeamName;
	}

	public String getSprintName() {
		return sprintName;
	}

	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
	}

	public Date getSprintStartDate() {
		return sprintStartDate;
	}

	public void setSprintStartDate(Date sprintStartDate) {
		this.sprintStartDate = sprintStartDate;
	}

	public Date getSprintEndDate() {
		return sprintEndDate;
	}

	public void setSprintEndDate(Date sprintEndDate) {
		this.sprintEndDate = sprintEndDate;
	}

}
