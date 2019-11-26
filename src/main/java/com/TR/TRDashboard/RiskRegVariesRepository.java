
package com.TR.TRDashboard;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface RiskRegVariesRepository extends CrudRepository<RiskRegistryVaries, Long> {
	
	public String riskQueryFetchAll = "select * from trdata.risk_registry reg inner join trdata.risk_registry_varies regvar on reg.riskid = regvar.riskid\r\n" + 
			"where regvar.riskvariesid in (select max(riskvariesid) from trdata.risk_registry_varies rrv where reg.riskid = rrv.riskid)";
	
	
	public String riskQueryFetchById = "select * from trdata.risk_registry reg inner join trdata.risk_registry_varies regvar on reg.riskid = regvar.riskid \r\n" + 
			"where regvar.riskvariesid in (select max(riskvariesid) from trdata.risk_registry_varies rrv where reg.riskid = rrv.riskid and reg.riskid = ?)";
	
	public String riskFetchByAssetSprint = "select * from trdata.risk_registry reg inner join trdata.risk_registry_varies regvar on reg.riskid = regvar.riskid \r\n" + 
			"where regvar.riskvariesid in (select max(riskvariesid) from trdata.risk_registry_varies rrv where reg.riskid = rrv.riskid and reg.asset = ? and reg.sprint = ?)";
	
	
	@Query(value = riskQueryFetchAll, nativeQuery = true)	
	List<RiskRegistryVaries> findAll();
	
	@Query(value = riskQueryFetchById, nativeQuery = true)	
	RiskRegistryVaries findById(long id);
	
	@Query(value = riskFetchByAssetSprint, nativeQuery = true)	
	List<RiskRegistryVaries> getRisksByAssetSprint(String asset, String sprint);

}
