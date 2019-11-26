package com.TR.service;

import java.util.List;
import java.util.Optional;

import com.TR.TRDashboard.Feedback;



public interface FeedbackInterface {
	
 public List<Feedback> getFeedbacks();
	 
	 public  Optional<Feedback> getFeedbackById(long id);
	 
	 public Feedback addFeedback(Feedback associate);
	 
	 public Feedback updateFeedback(Feedback associate);
	 
	

	 


}
