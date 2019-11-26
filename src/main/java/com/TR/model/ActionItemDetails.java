package com.TR.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "action_item_details", schema = "trdata")
public class ActionItemDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "action_item_id", insertable = false, updatable = false)
	private long actionItemid;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "action_item_id", nullable = false)
	private ActionItem actionItem;

	@Column(name = "classification", updatable = false)
	private String classification;
	@Column(name = "status", updatable = false)
	private String status;
	@JsonProperty("plannedEndDate")
	@Column(name = "planned_end_date", updatable = false, nullable = false)
	private Timestamp plannedEndDate;
	@Column(name = "closure_date", updatable = false, nullable = false)
	private Timestamp closureDate;
	@Column(name = "created_by", updatable = false, nullable = false)
	private String createdBy = "system";
	@Column(name = "updated_by", updatable = false, nullable = true)
	private String updatedBy;
	@Column(name = "created_date", updatable = false, nullable = false)
	private Timestamp createdDate = new Timestamp(System.currentTimeMillis());
	@Column(name = "updated_date", updatable = false, nullable = true)
	private Timestamp updatedDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getActionItemid() {
		return actionItemid;
	}

	public void setActionItemid(long actionItemid) {
		this.actionItemid = actionItemid;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getPlannedEndDate() {
		return plannedEndDate;
	}

	public void setPlannedEndDate(Timestamp plannedEndDate) {
		this.plannedEndDate = plannedEndDate;
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

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public ActionItem getActionItem() {
		return actionItem;
	}

	public void setActionItem(ActionItem actionItem) {
		this.actionItem = actionItem;
	}

}
