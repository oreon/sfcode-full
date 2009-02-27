package com.oreon.cerebrum.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import java.util.Set;
import org.apache.commons.collections.ListUtils;

import com.oreon.cerebrum.prescriptions.Item;
import com.oreon.cerebrum.service.ItemService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import org.witchcraft.model.support.Range;

/**
 * This is generated code - to edit code or override methods use - Item class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class ItemBackingBeanBase extends BaseBackingBean<Item> {

	protected Item item = new Item();

	protected ItemService itemService;

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public ItemService getItemService() {
		return this.itemService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Item> getBaseService() {
		return itemService;
	}

	public Item getEntity() {
		return getItem();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

	}

	public void reset() {
		item = new Item();
		resetRanges();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		if (id != 0)
			item = itemService.load(id);

	}

	@Override
	public String update() {

		return super.update();
	}

}
