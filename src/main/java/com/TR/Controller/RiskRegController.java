package com.TR.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.TR.TRDashboard.RiskRegistryVaries;
import com.TR.azuredevops.model.RiskRegistryRiskVaries;
import com.TR.service.RiskImpl;


@RestController 
@CrossOrigin(origins = "http://localhost:4200")
public class RiskRegController {
	
	@Autowired
	private RiskImpl riskImpl;
	
	 @PostMapping("/risks")   
	 void addRisk(@RequestBody RiskRegistryRiskVaries risk) {
	      riskImpl.addRisk(risk);
	 }
 
//    @PostMapping("/addrisks")   
//    RiskRegistryVaries addRisk(@RequestBody RiskRegistryVaries risk) {
//        return riskImpl.saveRisk(risk);
//    }
    
    @PutMapping("/risks")
    RiskRegistryVaries updateRisk(@RequestBody RiskRegistryVaries risk) {    	
    	return riskImpl.updateRisk(risk);
    }
    
    @GetMapping("/risks/{id}")
    public RiskRegistryVaries  getRisk(@PathVariable Long id) {
    	return  riskImpl.getRiskById(id);    	 
    }
    
    @GetMapping("/risks")
    public List<RiskRegistryVaries> getRisks() {
    	return  riskImpl.getRisks();    	 
    }   
    
    @GetMapping("/risks/{asset}/{sprint}")
    public List<RiskRegistryVaries> getRisksByAssetSprint(@PathVariable String asset, @PathVariable String sprint) {
    	return  riskImpl.getRisksByAssetSprint(asset, sprint);    	 
    }
    
    
  
}
