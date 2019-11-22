package com.TR.TRDashboard;

import java.sql.Timestamp;

import javax.persistence.Column;

public class DefectsDataModel {
	
	private String project = null;
	private String sprint;
	private String activeSprint;
	private String columnName;
	private Timestamp sprintenddate;
	private Timestamp sprintstartdate;
	private int bugCompletionRange;
	private int noofBugs;
	private boolean sprintOverRunFlag;
	private String status;
	private String issuetype;
	private String priority;
	private String assignee = null;
	private String label = null;
	private Timestamp created ;
	private Timestamp duedate ;
	private String issueId = null;
	private long sprintoverRunDays;
	private int Count =0;
	private int completionpercentage = 0;
	public boolean flag;
	
	private long timeestimate = 0;
	private long timespent = 0;
	private String effortStatus = null;
	private String qualityStatus = null;
	
	
	private String sprintstatus = "Green";
	
	private int totalitems = 0;
	


	

	

	public DefectsDataModel() {
		
	}
	
    public DefectsDataModel(String status, String issuetype, String priority, String assignee, String label,
            Timestamp created, Timestamp duedate, String issueId, boolean flag ,String project,String sprint,boolean sprintOverRunFlag,Timestamp Sprintstartdate ,Timestamp sprintenddate) {
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
     this.sprintOverRunFlag=sprintOverRunFlag;
     this.sprintenddate = sprintenddate;
     this.sprintstartdate = Sprintstartdate;
}
    public String getQualityStatus() {
		return qualityStatus;
	}

	public void setQualityStatus(String qualityStatus) {
		this.qualityStatus = qualityStatus;
	}
    public int getTotalitems() {
		return totalitems;
	}

	public void setTotalitems(int totalitems) {
		this.totalitems = totalitems;
	}
    
    public String getSprintstatus() {
		return sprintstatus;
	}

	public void setSprintstatus(String sprintstatus) {
		this.sprintstatus = sprintstatus;
	}
    
	public String getEffortStatus() {
		return effortStatus;
	}

	public void setEffortStatus(String effortStatus) {
		this.effortStatus = effortStatus;
	}
    
	public long getTimeestimate() {
		return timeestimate;
	}

	public void setTimeestimate(long timeestimate) {
		this.timeestimate = timeestimate;
	}

	public long getTimespent() {
		return timespent;
	}

	public void setTimespent(long timespent) {
		this.timespent = timespent;
	}

    public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

    public Timestamp getSprintstartdate() {
		return sprintstartdate;
	}
	public void setSprintstartdate(Timestamp sprintstartdate) {
		this.sprintstartdate = sprintstartdate;
	}
	
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
	
	public int getNoofBugs() {
		return noofBugs;
	}
	public void setNoofBugs(int noofBugs) {
		this.noofBugs = noofBugs;
	}
	public boolean isSprintOverRunFlag() {
		return sprintOverRunFlag;
	}
	public void setSprintOverRunFlag(boolean sprintOverRunFlag) {
		this.sprintOverRunFlag = sprintOverRunFlag;
	}
	public long getSprintoverRunDays() {
		return sprintoverRunDays;
	}
	public void setSprintoverRunDays(long sprintoverRunDays) {
		this.sprintoverRunDays = sprintoverRunDays;
	}
	
	public String getActiveSprint() {
		return activeSprint;
	}
	public void setActiveSprint(String activeSprint) {
		this.activeSprint = activeSprint;
	}




}
