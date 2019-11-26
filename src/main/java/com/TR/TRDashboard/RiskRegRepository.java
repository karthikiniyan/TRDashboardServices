package com.TR.TRDashboard;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface RiskRegRepository extends CrudRepository<RiskRegistry, Long> {
	
//	public String riskQueryFetchAll = "select * from risk_registry reg inner join risk_registry_varies regvar on reg.riskid = regvar.riskid\r\n" + 
//			"where regvar.riskvariesid in (select max(riskvariesid) from risk_registry_varies rrv where reg.riskid = rrv.riskid)";
//	
//	
//	public String riskQueryFetchById = "select * from risk_registry reg inner join risk_registry_varies regvar on reg.riskid = regvar.riskid \r\n" + 
//			"where regvar.riskvariesid in (select max(riskvariesid) from risk_registry_varies rrv where reg.riskid = rrv.riskid and reg.riskid = ?)";
//	
//	
//	@Query(value = riskQueryFetchAll, nativeQuery = true)	
//	List<RiskRegistry> findAll();
	


}
