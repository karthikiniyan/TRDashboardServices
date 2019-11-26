package com.TR.service;

import java.util.List;

import com.TR.model.ActionItem;

public interface IActionItemService {

	public ActionItem save(ActionItem item);

	public ActionItem getActionItemById(int id);

	public List<ActionItem> listActionitems();

}
