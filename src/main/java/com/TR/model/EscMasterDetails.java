package com.TR.model;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class EscMasterDetails {
	
	private long id;
	@JsonProperty("groupname")

	private String groupName = "ABC";

	private String assetName;

	private String sprint;
	
	@JsonProperty("startdate")
	private Date startDate = new Date();
	
	@JsonProperty("enddate")
	private Date endDate = new Date();
	
	
	@JsonProperty("escalationlevel")
	private String escalationLevel;

	private Timestamp dateOfEscalation;

	@JsonProperty("escalationstatement")
	private String escalationStatement;
	
	@JsonProperty("escalationby")
	private String escalationBy = "user";

	private String owner;
	
	@JsonProperty("worklog")
	private String workLog;
	
	@JsonProperty("loghistory")
	private String logHistory = "4";

	@JsonProperty("createdby")
	private String createdBy = "user";

	
	@JsonProperty("updatedby")
	private String updatedBy = "user";
	
	@JsonProperty("createddate")
	private Date createdDate = new Date() ;
	
	@JsonProperty("updateddate")
	private Date updatedDate = new Date();
	
	private long detailsId;

	private String severity = "high";
	
	@JsonProperty("plannedenddate")
	private Timestamp plannedEndDate;
	
	private String status;
	
	@JsonProperty("closuredate")
	private Timestamp closureDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getSprint() {
		return sprint;
	}
	public void setSprint(String sprint) {
		this.sprint = sprint;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public String getEscalationLevel() {
		return escalationLevel;
	}
	public void setEscalationLevel(String escalationLevel) {
		this.escalationLevel = escalationLevel;
	}
	public Timestamp getDateOfEscalation() {
		return dateOfEscalation;
	}
	public void setDateOfEscalation(Timestamp dateOfEscalation) {
		this.dateOfEscalation = dateOfEscalation;
	}
	public String getEscalationStatement() {
		return escalationStatement;
	}
	public void setEscalationStatement(String escalationStatement) {
		this.escalationStatement = escalationStatement;
	}
	public String getEscalationBy() {
		return escalationBy;
	}
	public void setEscalationBy(String escationBy) {
		this.escalationBy = escationBy;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getWorkLog() {
		return workLog;
	}
	public void setWorkLog(String workLog) {
		this.workLog = workLog;
	}
	public String getLogHistory() {
		return logHistory;
	}
	public void setLogHistory(String logHistory) {
		this.logHistory = logHistory;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	public long getDetailsId() {
		return detailsId;
	}
	public void setDetailsId(long detailsId) {
		this.detailsId = detailsId;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public Timestamp getPlannedEndDate() {
		return plannedEndDate;
	}
	public void setPlannedEndDate(Timestamp plannedEndDate) {
		this.plannedEndDate = plannedEndDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getClosureDate() {
		return closureDate;
	}
	public void setClosureDate(Timestamp closureDate) {
		this.closureDate = closureDate;
	}
}