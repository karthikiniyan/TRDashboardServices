package com.TR.service;

import java.util.List;
import java.util.Optional;

import com.TR.model.EscalationDetails;
import com.TR.model.EscMasterDetails;



public interface EscalationInterface {

	
	
	public List<EscalationDetails> getEscalations();
	  public EscalationDetails getescalationid(Long id);
	  public EscalationDetails  saveescalation(EscMasterDetails esc);
	  public EscalationDetails updateescalation(EscalationDetails details);
	//void saveescalation(escMasterDetails esc);
}
