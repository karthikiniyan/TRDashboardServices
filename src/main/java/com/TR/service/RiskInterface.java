package com.TR.service;



import java.util.List;

import com.TR.TRDashboard.RiskRegistryVaries;
import com.TR.azuredevops.model.RiskRegistryRiskVaries;





public interface RiskInterface {
	
	 public List<RiskRegistryVaries> getRisks();
	 
	 public RiskRegistryVaries getRiskById(long id);
	 
	 public RiskRegistryVaries saveRisk(RiskRegistryVaries riskRegistryVaries);
	 
	 public RiskRegistryVaries updateRisk(RiskRegistryVaries riskRegistryVaries);
	 
	 public List<RiskRegistryVaries> getRisksByAssetSprint(String asset, String sprint);

	 public void addRisk(RiskRegistryRiskVaries riskRegistryRiskVaries);
}
