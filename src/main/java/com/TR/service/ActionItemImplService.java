package com.TR.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TR.TRDashboard.ActionItemRepository;
import com.TR.TRDashboard.Audit;
import com.TR.model.ActionItem;
import com.TR.model.ActionItemDetails;

@Service
public class ActionItemImplService implements IActionItemService {

	@Autowired
	private ActionItemRepository actionItemRepository;

	@Override
	public ActionItem save(ActionItem item) {
		// new Audit("jey", "jey", new Timestamp(System.currentTimeMillis()), new
		// Timestamp(System.currentTimeMillis()));
		actionItemRepository.save(item);
		System.out.println("hjh" + item);
		return item;
	}

	@Override
	public ActionItem getActionItemById(int id) {

		ActionItem item = actionItemRepository.findById(Long.valueOf(id)).get();
		System.out.println(item.getActionItemDetails().size());

		List<ActionItemDetails> aid = new ArrayList<ActionItemDetails>();
		aid.add(item.getActionItemDetails().get(item.getActionItemDetails().size() - 1));
		item.setActionItemDetails(aid);
		/*
		 * for(int i=0;i<item1.getActionItemDetails().size()-1;i++) {
		 * item1.getActionItemDetails().remove(i); }
		 */

		return item;
	}

	@Override
	public List<ActionItem> listActionitems() {
		List<ActionItem> actionItems = actionItemRepository.findAll();
		List<ActionItemDetails> aid;
		for (ActionItem item : actionItems) {
			aid = new ArrayList<ActionItemDetails>();
			aid.add(item.getActionItemDetails().get(item.getActionItemDetails().size() - 1));
			item.setActionItemDetails(aid);

		}
		return actionItems;
	}

}
