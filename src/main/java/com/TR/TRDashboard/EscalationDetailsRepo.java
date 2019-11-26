package com.TR.TRDashboard;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.TR.model.EscalationDetails;

@Repository
public interface EscalationDetailsRepo extends CrudRepository<EscalationDetails, Long> {

	
	 
	  public String findByidquery = "select * from trdata.escalationmaster em inner join trdata.escalationdetails ed on em.id= ed.id \r\n"
				+ "where ed.details_id in (select max(details_id) from trdata.escalationdetails escd where em.id= escd.id  and escd.id=?)";

	public String query = "select * from trdata.escalationmaster em inner join trdata.escalationdetails ed on em.id= ed.id \r\n"
			+ "where ed.details_id in (select max(details_id) from trdata.escalationdetails escd where em.id= escd.id )";

	@Query(value = query, nativeQuery = true)
	
	List<EscalationDetails> findAll();
	
	
	
	@Query(value = findByidquery, nativeQuery = true)
	
	EscalationDetails findbyId(long id);
	
	//<S extends EscalationDetails> S save(S entity);
}
