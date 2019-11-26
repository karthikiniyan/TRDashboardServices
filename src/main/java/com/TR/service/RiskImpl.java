package com.TR.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TR.TRDashboard.RiskRegRepository;
import com.TR.TRDashboard.RiskRegVariesRepository;
import com.TR.TRDashboard.RiskRegistry;
import com.TR.TRDashboard.RiskRegistryVaries;
import com.TR.azuredevops.model.RiskRegistryRiskVaries;



@Repository
public class RiskImpl implements RiskInterface{
	
	@Autowired
	private RiskRegRepository riskRepository;
	
	@Autowired
	private RiskRegVariesRepository riskRegVariesRepository;

	@Override
	public List<RiskRegistryVaries> getRisks() {
		
		return (List<RiskRegistryVaries>) riskRegVariesRepository.findAll();
	}

	@Override
	public RiskRegistryVaries getRiskById(long id) {		
		
		return  riskRegVariesRepository.findById(id);
	}	

	@Override
	public RiskRegistryVaries updateRisk(RiskRegistryVaries riskRegistryVaries) {
		
		RiskRegistry riskRegistry = riskRegistryVaries.getRiskRegistry();
		riskRegistry.setWorkloghistory(riskRegistry.getWorklog() + "," + riskRegistry.getWorkloghistory());
		riskRegistryVaries.setRiskrank(riskRegistryVaries.getProbability() * riskRegistryVaries.getImpact());
		riskRegistryVaries.setRiskvariesid(-1);
		return riskRegVariesRepository.save(riskRegistryVaries); 	
	}

	@Override
	public List<RiskRegistryVaries> getRisksByAssetSprint(String asset, String sprint) {

		return (List<RiskRegistryVaries>) riskRegVariesRepository.getRisksByAssetSprint(asset, sprint);    	
	}
	
	@Override
	public void addRisk(RiskRegistryRiskVaries riskRegistryRiskVaries) {
		
		RiskRegistry  riskRegistry = setRiskRegistry(riskRegistryRiskVaries);		
		List<RiskRegistryVaries> riskRegistryVariesList = new ArrayList<RiskRegistryVaries>();		
		RiskRegistryVaries  riskRegistryVaries = setRiskRegistryVaries(riskRegistryRiskVaries);	
		riskRegistryVariesList.add(riskRegistryVaries);
		riskRegistry.setRiskRegistryVaries(riskRegistryVariesList);
		riskRegistryVaries.setRiskRegistry(riskRegistry);
		riskRegVariesRepository.save(riskRegistryVaries); 	
	}
	
	
	private RiskRegistry setRiskRegistry(RiskRegistryRiskVaries riskRegistryVaries) {
		
		RiskRegistry  riskRegistry = new RiskRegistry();		
		riskRegistry.setGroupname(riskRegistryVaries.getGroupname());
		riskRegistry.setAsset(riskRegistryVaries.getAsset());
		riskRegistry.setSprint(riskRegistryVaries.getSprint());
		riskRegistry.setRiskstatement(riskRegistryVaries.getRiskstatement());
		riskRegistry.setRiskowner(riskRegistryVaries.getRiskowner());
		riskRegistry.setRiskidentifieddate(riskRegistryVaries.getRiskidentifieddate());
		riskRegistry.setRiskidentifiedby(riskRegistryVaries.getRiskidentifiedby());
		riskRegistry.setWorklog(riskRegistryVaries.getWorklog());
		riskRegistry.setWorkloghistory(riskRegistry.getWorklog() + "," + riskRegistryVaries.getWorkloghistory());
		return riskRegistry;	
	}
	
	private RiskRegistryVaries setRiskRegistryVaries(RiskRegistryRiskVaries riskRegistryVaries) {
		
		RiskRegistryVaries  riskRegistryVaries1 = new RiskRegistryVaries();
		riskRegistryVaries1.setProbability(riskRegistryVaries.getProbability());
		riskRegistryVaries1.setImpact(riskRegistryVaries.getImpact());
		riskRegistryVaries1.setRiskrank(riskRegistryVaries.getProbability() * riskRegistryVaries.getImpact());
		riskRegistryVaries1.setRiskapproach(riskRegistryVaries.getRiskapproach());
		riskRegistryVaries1.setRiskstatus(riskRegistryVaries.getRiskstatus());
		riskRegistryVaries1.setPlannedenddate(riskRegistryVaries.getPlannedenddate());
		riskRegistryVaries1.setRiskclosuredate(riskRegistryVaries.getRiskclosuredate());		
		return riskRegistryVaries1;	
		
	}

	@Override
	public RiskRegistryVaries saveRisk(RiskRegistryVaries riskRegistryVaries) {
		// TODO Auto-generated method stub
		return null;
	}

}
