package com.TR.TRDashboard;

import java.io.Serializable;
//import java.security.Timestamp;
import java.util.Date;
import java.sql.Timestamp;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tblfeedback",schema="trdata")
public class Feedback implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_id")
	private Long ID;
	@Column(name = "assetname")
	private String assetName;
	@Column(name = "sprint")
	private int sprint;
	@Column(name = "Owner")
	private String owner;
	@Column(name = "qualityreleaserate")
	private int qualityReleaseRate;
	@Column(name = "qualityreleaseremarks")
	private String qualityReleaseRemarks;
	@Column(name = "qualityreleaseguidance")
	private String qualityReleaseGuidance;
	@Column(name = "functionalqualityrate")
	private int functionalQualityRate;
	@Column(name = "functionalqualityremarks")
	private String functionalQualityRemarks;
	@Column(name = "functionalqualityguidance")
	private String functionalQualityGuidance;
	@Column(name = "technicalqualityrate")
	private int technicalQualityRate;
	@Column(name = "technicalqualityremarks")
	private String technicalQualityRemarks;
	@Column(name = "technicalqualityguidance")
	private String technicalQualityGuidance;
	@Column(name = "ontimedeliveryrank")
	private int ontimeDeliveryRank;
	@Column(name = "ontimedeliveryremarks")
	private String ontimeDeliveryRemarks;
	@Column(name = "ontimedeliveryguidance")
	private String ontimeDeliveryGuidance;
	@Column(name = "innovationrank")
	private int innovationRank;
	@Column(name = "innovationremarks")
	private String innovationRemarks;
	@Column(name = "innovationguidance")
	private String innovationGuidance;
	@Column(name = "candrrank")
	private int communicationRank;
	@Column(name = "candrremarks")
	private String communicationRemarks;
	@Column(name = "candrguidance")
	private String communicationGuidance;
	@Column(name = "areasofdelight")
	private String areasOfDelight;
	@Column(name = "areaofimprovement")
	private String areaOfImprovement;
	@Column(name = "totalrank")
	private float totalrank;
	@Column(name = "createdby")
	private String createdBy = "Guest";
	@Column(name = "updatedby")
	private String updatedBy = "Guest";

	@Column(name = "startdate")
	private Timestamp startdate;

	@Column(name = "enddate")
	private Timestamp enddate;
	@Column(name = "groupname")
	private String groupName;

	@Column(name = "created_date")
	private Date createdDate = new Date();

	@Column(name = "updated_date")
	private Date updatedDate = new Date();
	
	@Column(name = "feedbackdate")
	private Timestamp feedbackDate;

	
	public Timestamp getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(Timestamp feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public float getTotalrank() {
		return totalrank;
	}

	public void setTotalrank(float totalrank) {
		this.totalrank = totalrank;
	}

	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Timestamp getStartdate() {
		return startdate;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	public Timestamp getEnddate() {
		return enddate;
	}

	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}

	public int getCommunicationRank() {
		return communicationRank;
	}

	public void setCommunicationRank(int communicationRank) {
		this.communicationRank = communicationRank;
	}

	public String getCommunicationRemarks() {
		return communicationRemarks;
	}

	public void setCommunicationRemarks(String communicationRemarks) {
		this.communicationRemarks = communicationRemarks;
	}

	public String getCommunicationGuidance() {
		return communicationGuidance;
	}

	public void setCommunicationGuidance(String communicationGuidance) {
		this.communicationGuidance = communicationGuidance;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public int getSprint() {
		return sprint;
	}

	public void setSprint(int sprint) {
		this.sprint = sprint;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getQualityReleaseRate() {
		return qualityReleaseRate;
	}

	public void setQualityReleaseRate(int qualityReleaseRate) {
		this.qualityReleaseRate = qualityReleaseRate;
	}

	public String getQualityReleaseRemarks() {
		return qualityReleaseRemarks;
	}

	public void setQualityReleaseRemarks(String qualityReleaseRemarks) {
		this.qualityReleaseRemarks = qualityReleaseRemarks;
	}

	public String getQualityReleaseGuidance() {
		return qualityReleaseGuidance;
	}

	public void setQualityReleaseGuidance(String qualityReleaseGuidance) {
		this.qualityReleaseGuidance = qualityReleaseGuidance;
	}

	public int getFunctionalQualityRate() {
		return functionalQualityRate;
	}

	public void setFunctionalQualityRate(int functionalQualityRate) {
		this.functionalQualityRate = functionalQualityRate;
	}

	public String getFunctionalQualityRemarks() {
		return functionalQualityRemarks;
	}

	public void setFunctionalQualityRemarks(String functionalQualityRemarks) {
		this.functionalQualityRemarks = functionalQualityRemarks;
	}

	public String getFunctionalQualityGuidance() {
		return functionalQualityGuidance;
	}

	public void setFunctionalQualityGuidance(String functionalQualityGuidance) {
		this.functionalQualityGuidance = functionalQualityGuidance;
	}

	public int getTechnicalQualityRate() {
		return technicalQualityRate;
	}

	public void setTechnicalQualityRate(int technicalQualityRate) {
		this.technicalQualityRate = technicalQualityRate;
	}

	public String getTechnicalQualityRemarks() {
		return technicalQualityRemarks;
	}

	public void setTechnicalQualityRemarks(String technicalQualityRemarks) {
		this.technicalQualityRemarks = technicalQualityRemarks;
	}

	public String getTechnicalQualityGuidance() {
		return technicalQualityGuidance;
	}

	public void setTechnicalQualityGuidance(String technicalQualityGuidance) {
		this.technicalQualityGuidance = technicalQualityGuidance;
	}

	public int getOntimeDeliveryRank() {
		return ontimeDeliveryRank;
	}

	public void setOntimeDeliveryRank(int ontimeDeliveryRank) {
		this.ontimeDeliveryRank = ontimeDeliveryRank;
	}

	public String getOntimeDeliveryRemarks() {
		return ontimeDeliveryRemarks;
	}

	public void setOntimeDeliveryRemarks(String ontimeDeliveryRemarks) {
		this.ontimeDeliveryRemarks = ontimeDeliveryRemarks;
	}

	public String getOntimeDeliveryGuidance() {
		return ontimeDeliveryGuidance;
	}

	public void setOntimeDeliveryGuidance(String ontimeDeliveryGuidance) {
		this.ontimeDeliveryGuidance = ontimeDeliveryGuidance;
	}

	public int getInnovationRank() {
		return innovationRank;
	}

	public void setInnovationRank(int innovationRank) {
		this.innovationRank = innovationRank;
	}

	public String getInnovationRemarks() {
		return innovationRemarks;
	}

	public void setInnovationRemarks(String innovationRemarks) {
		this.innovationRemarks = innovationRemarks;
	}

	public String getInnovationGuidance() {
		return innovationGuidance;
	}

	public void setInnovationGuidance(String innovationGuidance) {
		this.innovationGuidance = innovationGuidance;
	}

	public String getAreasOfDelight() {
		return areasOfDelight;
	}

	public void setAreasOfDelight(String areasOfDelight) {
		this.areasOfDelight = areasOfDelight;
	}

	public String getAreaOfImprovement() {
		return areaOfImprovement;
	}

	public void setAreaOfImprovement(String areaOfImprovement) {
		this.areaOfImprovement = areaOfImprovement;
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

	public void setCreatedDate(Date createdDate) {

		this.createdDate = createdDate;
	}

}
