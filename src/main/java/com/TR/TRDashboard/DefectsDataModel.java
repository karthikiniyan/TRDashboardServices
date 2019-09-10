package com.TR.TRDashboard;

import java.sql.Timestamp;

public class DefectsDataModel {
	
	private String project;
	private String sprint;
	private String columnName;
	private Timestamp sprintenddate;
	private int bugCompletionRange;
	
	


	public boolean flag;
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public DefectsDataModel() {
		
	}
	
	public DefectsDataModel(String status, String issuetype, String priority, String assignee, String label,
			Timestamp created, Timestamp duedate, String issueId, boolean flag ,String project,String sprint) {
		super();
		this.status = status;
		this.issuetype = issuetype;
		this.priority = priority;
		this.assignee = assignee;
		this.label = label;
		this.created = created;
		this.duedate = duedate;
		this.issueId = issueId;
		this.flag=flag;
		this.project=project;
		this.sprint=sprint;
	}


	private String status;
	private String issuetype;
	private String priority;
	private String assignee = null;
	private String label = null;
	private Timestamp created ;
	private Timestamp duedate ;
	private String issueId = null;
	
	public String getIssueId() {
		return issueId;
	}
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getDuedate() {
		return duedate;
	}
	public void setDuedate(Timestamp duedate) {
		this.duedate = duedate;
	}

	
	private int Count =0;
	private int completionpercentage = 0;
	
	public int getCompletionpercentage() {
		return completionpercentage;
	}
	public void setCompletionpercentage(int completionpercentage) {
		this.completionpercentage = completionpercentage;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getSprint() {
		return sprint;
	}
	public void setSprint(String sprint) {
		this.sprint = sprint;
	}
	public String getIssuetype() {
		return issuetype;
	}
	public void setIssuetype(String issuetype) {
		this.issuetype = issuetype;
	}
	
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	
	public Timestamp getSprintenddate() {
		return sprintenddate;
	}
	public void setSprintenddate(Timestamp sprintenddate) {
		this.sprintenddate = sprintenddate;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getBugCompletionRange() {
		return bugCompletionRange;
	}
	public void setBugCompletionRange(int bugCompletionRange) {
		this.bugCompletionRange = bugCompletionRange;
	}

}
