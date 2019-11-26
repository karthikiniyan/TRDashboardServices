package com.TR.TRDashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.TR.model.ActionItem;

@Repository
public interface ActionItemRepository extends CrudRepository<ActionItem, Long> {

	@Query(value = "from ActionItem")
	List<ActionItem> findAll();

}
