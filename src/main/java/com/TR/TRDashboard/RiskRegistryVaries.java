
package com.TR.TRDashboard;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "risk_registry_varies", schema = "trdata")
public class RiskRegistryVaries {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long riskvariesid;
	
	
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "riskid", nullable = false)
    private RiskRegistry riskRegistry;

	private int probability;
	
    private int impact;
    
	private int riskrank;
	
	private String riskapproach;	
	
	private Date plannedenddate;
	
	private String riskstatus;
	
	private Date riskclosuredate;
	
	private String createdby = "Guest";
	
	private String updatedby = "Guest";
	
	private Date createddate = new Date();
	
	private Date updateddate = new Date();
	

	public RiskRegistry getRiskRegistry() {
		return riskRegistry;
	}

	public void setRiskRegistry(RiskRegistry riskRegistry) {
		this.riskRegistry = riskRegistry;
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

	public Date getPlannedenddate() {
		return plannedenddate;
	}

	public void setPlannedenddate(Date plannedenddate) {
		this.plannedenddate = plannedenddate;
	}

	public String getRiskstatus() {
		return riskstatus;
	}

	public void setRiskstatus(String riskstatus) {
		this.riskstatus = riskstatus;
	}

	public Date getRiskclosuredate() {
		return riskclosuredate;
	}

	public void setRiskclosuredate(Date riskclosuredate) {
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
