package com.TR.TRDashboard;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long> {

}
