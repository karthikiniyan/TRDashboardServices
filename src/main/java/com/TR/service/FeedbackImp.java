package com.TR.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TR.TRDashboard.Feedback;
import com.TR.TRDashboard.FeedbackRepository;

@Service
public class FeedbackImp implements FeedbackInterface {
	@Autowired
	private FeedbackRepository repository;

	@Override
	public List<Feedback> getFeedbacks() {

		Iterable<Feedback> result = repository.findAll();
		return (List<Feedback>) result;
	}

	@Override
	public Optional<Feedback> getFeedbackById(long id) {
		return repository.findById(id);
	}

	@Override
	public Feedback addFeedback(Feedback feedback) {
		feedback.setTotalrank((feedback.getFunctionalQualityRate() + feedback.getQualityReleaseRate()
				+ feedback.getTechnicalQualityRate() + feedback.getOntimeDeliveryRank() + feedback.getInnovationRank()
				+ feedback.getCommunicationRank()) / 6);
		return repository.save(feedback);
	}

	@Override
	public Feedback updateFeedback(Feedback feedback) {
		return repository.save(feedback);
	}

}
