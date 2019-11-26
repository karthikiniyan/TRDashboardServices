package com.TR.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.TR.TRDashboard.Feedback;
import com.TR.service.FeedbackImp;
import com.TR.service.FeedbackInterface;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FeedbackController {

	@Autowired
	private FeedbackInterface feedbackImp;

	@GetMapping("/feedback")
	public List<Feedback> findAll() {
		Iterable<Feedback> result = feedbackImp.getFeedbacks();
		return (List<Feedback>) result;

	}

	@GetMapping("/feedback/{id}")
	Optional<Feedback> getRisk(@PathVariable Long id) {
		return feedbackImp.getFeedbackById(id);

	}

	@PostMapping("/feedback")
	Feedback addRisk(@RequestBody Feedback feedback) {

		System.out.println("in add risk");
//		feedback.setTotalrank((feedback.getFunctionalQualityRate() + feedback.getQualityReleaseRate()
//				+ feedback.getTechnicalQualityRate() + feedback.getOntimeDeliveryRank() + feedback.getInnovationRank()
//				+ feedback.getCommunicationRank()) / 6);
//		System.out.println(feedback.getTotalrank());
		return feedbackImp.addFeedback(feedback);

	}

	@PutMapping("/feedback/{id}")
	Feedback updateJob(@RequestBody Feedback feedback, @PathVariable int id) {
		return feedbackImp.addFeedback(feedback);

	}

}
