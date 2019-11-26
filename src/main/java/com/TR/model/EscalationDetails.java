package com.TR.model;


import java.sql.Timestamp;
import java.util.Date;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "escalationdetails", schema = "trdata")
public class EscalationDetails {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "details_id")
	private long detailsId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "id",nullable = false)
	private Escalation escalation;

	

	
	@Column(name = "severity")
	private String severity;
	@Column(name = "plannedenddate")
	private Date plannedEndDate;
	@Column(name = "status")
	private String status;
	@Column(name = "closuredate")
	private Timestamp closureDate;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "updatedby")
	private String updatedBy;
	@Column(name = "createddate")
	private Date createdDate;
	@Column(name = "updatedate")
	private Date updateDate;

	
	public Escalation getEscalation() {
		return escalation;
	}

	public void setEscalation(Escalation escalation) {
		this.escalation = escalation;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public Date getPlannedEndDate() {
		return plannedEndDate;
	}

	public void setPlannedEndDate(Date date) {
		this.plannedEndDate = date;
	}

	public long getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(long detailsId) {
		this.detailsId = detailsId;
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

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdatedDate(Date date) {
		this.updateDate = date;
	}

}
