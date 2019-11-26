package com.TR.azuredevops.model;

import java.sql.Timestamp;
import java.util.Date;

public class RiskRegistryRiskVaries {
	
	private long riskid;
	
	private String groupname = "BFS";
	
    private String asset = "Online365";
    
	private String sprint = "EStatement";
	
	private Date startdate;
	
	private Date enddate;	
	
	private String riskstatement;
	
	private String riskowner;
	
	private Timestamp riskidentifieddate;
	
	private String riskidentifiedby;
	
	private String worklog;
	
	private String workloghistory;

    private long riskvariesid;

	private int probability;
	
    private int impact;
    
	private int riskrank;
	
	private String riskapproach;	
	
	private Timestamp plannedenddate;
	
	private String riskstatus;
	
	private Timestamp riskclosuredate;
		
	private String createdby = "Guest";
	
	private String updatedby = "Guest";	
	

	public long getRiskid() {
		return riskid;
	}

	public void setRiskid(long riskid) {
		this.riskid = riskid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public String getSprint() {
		return sprint;
	}

	public void setSprint(String sprint) {
		this.sprint = sprint;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getRiskstatement() {
		return riskstatement;
	}

	public void setRiskstatement(String riskstatement) {
		this.riskstatement = riskstatement;
	}

	public String getRiskowner() {
		return riskowner;
	}

	public void setRiskowner(String riskowner) {
		this.riskowner = riskowner;
	}

	public Timestamp getRiskidentifieddate() {
		return riskidentifieddate;
	}

	public void setRiskidentifieddate(Timestamp riskidentifieddate) {
		this.riskidentifieddate = riskidentifieddate;
	}

	public String getRiskidentifiedby() {
		return riskidentifiedby;
	}

	public void setRiskidentifiedby(String riskidentifiedby) {
		this.riskidentifiedby = riskidentifiedby;
	}

	public String getWorklog() {
		return worklog;
	}

	public void setWorklog(String worklog) {
		this.worklog = worklog;
	}

	public String getWorkloghistory() {
		return workloghistory;
	}

	public void setWorkloghistory(String workloghistory) {
		this.workloghistory = workloghistory;
	}

	public long getRiskvariesid() {
		return riskvariesid;
	}

	public void setRiskvariesid(long riskvariesid) {
		this.riskvariesid = riskvariesid;
	}

	public int getProbability() {
		return probability;
	}

	public void setProbability(int probability) {
		this.probability = probability;
	}

	public int getImpact() {
		return impact;
	}

	public void setImpact(int impact) {
		this.impact = impact;
	}

	public int getRiskrank() {
		return riskrank;
	}

	public void setRiskrank(int riskrank) {
		this.riskrank = riskrank;
	}

	public String getRiskapproach() {
		return riskapproach;
	}

	public void setRiskapproach(String riskapproach) {
		this.riskapproach = riskapproach;
	}

	public Timestamp getPlannedenddate() {
		return plannedenddate;
	}

	public void setPlannedenddate(Timestamp plannedenddate) {
		this.plannedenddate = plannedenddate;
	}

	public String getRiskstatus() {
		return riskstatus;
	}

	public void setRiskstatus(String riskstatus) {
		this.riskstatus = riskstatus;
	}

	public Timestamp getRiskclosuredate() {
		return riskclosuredate;
	}

	public void setRiskclosuredate(Timestamp riskclosuredate) {
		this.riskclosuredate = riskclosuredate;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}		

}
