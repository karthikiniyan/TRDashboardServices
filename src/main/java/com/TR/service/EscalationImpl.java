package com.TR.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.TR.TRDashboard.EscalationDetailsRepo;
import com.TR.model.Escalation;
import com.TR.model.EscalationDetails;
import com.TR.model.EscMasterDetails;


@Component
public class EscalationImpl implements EscalationInterface {

	@Autowired
	
	EscalationDetailsRepo rep;
	@Override
	public List<EscalationDetails> getEscalations() {
		 List<EscalationDetails> result=rep.findAll();
		 
		  return result; 
	}
	
	@Override
	public EscalationDetails getescalationid(Long id) {
		return rep.findbyId(id);
	
	}
	@Override
	public EscalationDetails saveescalation(EscMasterDetails esc) {
		
	

		Escalation  ess = setEsc(esc);		
		List<EscalationDetails> EscalationDetailsList = new ArrayList<EscalationDetails>();		
		EscalationDetails  escDetailsValue = setEscDetails(esc);	
		EscalationDetailsList.add(escDetailsValue);
		ess.setEscalationdetails(EscalationDetailsList);
		escDetailsValue.setEscalation(ess);
		 return  rep.save(escDetailsValue); 
		
	}
	@Override
	public EscalationDetails updateescalation(EscalationDetails details ) {
		details.setDetailsId(-1);
		Escalation escal= details.getEscalation();
		escal.setLogHistory(escal.getWorkLog()+","+escal.getLogHistory());
		 return rep.save(details); 
	}

	

	private Escalation setEsc(EscMasterDetails esc) {
		Escalation es = new  Escalation();
		es.setAssetName(esc.getAssetName());
		es.setSprint(esc.getSprint());
		es.setEscalationLevel(esc.getEscalationLevel());
		es.setDateOfEscalation(esc.getDateOfEscalation());
		es.setOwner(esc.getOwner());
		es.setWorkLog(esc.getWorkLog());
		es.setGroupName(esc.getGroupName());
		es.setStartDate(esc.getStartDate());
		es.setEndDate(esc.getEndDate());
		es.setEscalationStatement(esc.getEscalationStatement());
		es.setEscationBy(esc.getEscalationBy());
		es.setLogHistory(esc.getLogHistory());
		es.setEscalationLevel(esc.getEscalationLevel());
		es.setCreatedBy(esc.getCreatedBy());
		es.setCreatedDate(esc.getCreatedDate());
		es.setUpdatedBy(esc.getUpdatedBy());
		es.setUpdatedDate(esc.getUpdatedDate());
		return es;	
	}
	
	private EscalationDetails setEscDetails(EscMasterDetails esc) {
		EscalationDetails esd = new EscalationDetails();
		esd.setClosureDate(esc.getClosureDate());
		esd.setStatus(esc.getStatus());
		esd.setSeverity(esc.getSeverity());
		esd.setPlannedEndDate(esc.getPlannedEndDate());
		esd.setCreatedBy(esc.getCreatedBy());
		esd.setCreatedDate(esc.getCreatedDate());
		esd.setUpdatedBy(esc.getUpdatedBy());
		esd.setUpdatedDate(esc.getUpdatedDate());
		return esd;
	}

	

}
