package com.TR.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TR.model.ActionItem;
import com.TR.model.ActionItemDetails;
import com.TR.service.IActionItemService;

@RestController
@RequestMapping("/master")
public class ActionItemController {
	@Autowired
	private IActionItemService actionItem;

	@GetMapping("/getactionitem/{id}")
	public ActionItem getActionitemById(@PathVariable int id) {
		System.out.println("dsa");
		ActionItem item = actionItem.getActionItemById(id);
		return item;
	}

	@GetMapping("/getactionitems")
	public List<ActionItem> getActionitems() {
		System.out.println("dsa");
		List<ActionItem> item = actionItem.listActionitems();
		return item;
	}

	@PostMapping("/actionitem/save")
	public ActionItem saveActionitem(@RequestBody ActionItem item) {
		System.out.println("save");
		for (ActionItemDetails aid : item.getActionItemDetails()) {
			aid.setActionItem(item);
		}
		actionItem.save(item);

		return item;
	}

}
