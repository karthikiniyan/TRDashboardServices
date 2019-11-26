package com.TR.TRDashboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



import org.springframework.stereotype.Repository;

import com.TR.model.Escalation;





@Repository
public interface EscalationRepo extends CrudRepository<Escalation, Long> {

			
	 
	  public String findByidquery = "select * from escalationmaster em inner join escalationdetails ed on em.id= ed.id \r\n"+" where ed.details_id in (select max(details_id) from escalationdetails escd where em.id= escd.id  and escd.id=?)";

	public String query = "select * from escalationmaster em inner join escalationdetails ed on em.id= ed.id \r\n"
			+ "where ed.details_id in (select max(details_id) from escalationdetails escd where em.id= escd.id )";

	@Query(value = query, nativeQuery = true)
	
	List<Escalation> findAll();
	
	
	
	@Query(value = findByidquery, nativeQuery = true)
	
	List<Escalation> findbyId(long id);
	
	<S extends Escalation> S save(S entity);
	
	
}

