package com.TR.TRDashboard;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "risk_registry", schema = "trdata")
public class RiskRegistry {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long riskid;

	@JsonIgnore
	@OneToMany(mappedBy = "riskRegistry", cascade=CascadeType.ALL)
    private List<RiskRegistryVaries> riskRegistryVaries;		
	
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
		
	private String createdby = "Guest";
	
	private String updatedby = "Guest";
	
	private Date createddate = new Date();
	
	private Date updateddate = new Date();
	

	public List<RiskRegistryVaries> getRiskRegistryVaries() {
		return riskRegistryVaries;
	}

	public void setRiskRegistryVaries(List<RiskRegistryVaries> riskRegistryVaries) {
		this.riskRegistryVaries = riskRegistryVaries;
	}

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

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public Date getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}	

}
