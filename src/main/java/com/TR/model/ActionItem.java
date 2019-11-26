package com.TR.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "action_item", schema = "trdata")
public class ActionItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@OneToMany(mappedBy = "actionItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<ActionItemDetails> actionItemDetails;
	@Column(name = "group_name", updatable = true, nullable = false)
	private String groupName;
	@Column(name = "asset_name", updatable = true, nullable = false)
	private String assetName;
	@Column(name = "sprint", updatable = true, nullable = false)
	private String sprint;
	@Column(name = "start_date", updatable = true, nullable = false)
	private Timestamp startDate;
	@Column(name = "end_date", updatable = true, nullable = false)
	private Timestamp endDate;
	@Column(name = "action_item", updatable = true, nullable = false)
	private String actionItem;
	@Column(name = "owner", updatable = true, nullable = false)
	private String owner;
	@Column(name = "issue_identified_date", updatable = true, nullable = false)
	private Timestamp issueIdentifiedDate;
	@Column(name = "issue_raised_by", updatable = true, nullable = false)
	private String issueRaisedBy;
	@Column(name = "work_log", updatable = true, nullable = false)
	private String workLog;
	@Column(name = "log_history", updatable = true, nullable = false)
	private String logHistory;
	@Column(name = "created_by", updatable = true, nullable = false)
	private String createdBy = "system";
	@Column(name = "updated_by", updatable = true, nullable = true)
	private String updatedBy;
	@Column(name = "created_date", updatable = true, nullable = false)
	private Timestamp createdDate = new Timestamp(System.currentTimeMillis());
	@Column(name = "updated_date", updatable = true, nullable = true)
	private Timestamp updatedDate;

	/*
	 * @Embedded
	 * 
	 * @AttributeOverrides({
	 * 
	 * @AttributeOverride( name = "createdBy", column = @Column(name =
	 * "created_by")),
	 * 
	 * @AttributeOverride( name = "updatedBy", column = @Column(name =
	 * "updated_by")),
	 * 
	 * @AttributeOverride( name = "createdDate", column = @Column(name =
	 * "created_date")),
	 * 
	 * @AttributeOverride( name = "updatedDate", column = @Column(name =
	 * "updated_date")) }) private Audit audit;
	 */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<ActionItemDetails> getActionItemDetails() {
		return actionItemDetails;
	}

	public void setActionItemDetails(List<ActionItemDetails> actionItemDetails) {
		this.actionItemDetails = actionItemDetails;
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

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public String getActionItem() {
		return actionItem;
	}

	public void setActionItem(String actionItem) {
		this.actionItem = actionItem;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Timestamp getIssueIdentifiedDate() {
		return issueIdentifiedDate;
	}

	public void setIssueIdentifiedDate(Timestamp issueIdentifiedDate) {
		this.issueIdentifiedDate = issueIdentifiedDate;
	}

	public String getIssueRaisedBy() {
		return issueRaisedBy;
	}

	public void setIssueRaisedBy(String issueRaisedBy) {
		this.issueRaisedBy = issueRaisedBy;
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

}
