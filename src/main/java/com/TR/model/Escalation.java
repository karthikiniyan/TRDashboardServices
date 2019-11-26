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

@Entity

@Table(name = "escalationmaster",schema = "trdata")
public class Escalation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "escalation")
	private List<EscalationDetails> escalationdetails;
	
	@Column(name = "groupname")
	private String groupName;

	@Column(name = "assetname")
	private String assetName;

	@Column(name = "sprint")
	private String sprint;
	@Column(name = "startdate")
	private Date startDate;
	@Column(name = "enddate")
	private Date endDate;
	@Column(name = "escalationlevel")
	private String escalationLevel;

	@Column(name = "dateofescalation")
	private Timestamp dateOfEscalation;

	@Column(name = "escalationstatement")
	private String escalationStatement;

	@Column(name = "escalationby")
	private String escationBy;
	@Column(name = "owner")
	private String owner;

	@Column(name = "worklog")

	private String workLog;
	@Column(name = "loghistory")

	private String logHistory;

	@Column(name = "createdby")
	private String createdBy;

	@Column(name = "updatedby")
	private String updatedBy;

	@Column(name = "Createddate")
	private Date createdDate;
	@Column(name = "updateddate")
	private Date updatedDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	  public List<EscalationDetails> getEscalationdetails() { return
	  escalationdetails; }
	  public void setEscalationdetails(List<EscalationDetails>
	  escalationdetails)
	  { this.escalationdetails = escalationdetails; }
	 
	
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
	public void setStartDate(Date date) {
		this.startDate = date;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date date) {
		this.endDate = date;
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
	public String getEscationBy() {
		return escationBy;
	}
	public void setEscationBy(String escationBy) {
		this.escationBy = escationBy;
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
	public void setCreatedDate(Date date) {
		this.createdDate = date;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date date) {
		this.updatedDate = date;
	}
	
}

	