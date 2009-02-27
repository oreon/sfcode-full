
/**
 * This is generated code - to edit code or override methods use - Item class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.cerebrum.service.impl;

import com.oreon.cerebrum.prescriptions.Item;
import com.oreon.cerebrum.service.ItemService;
import com.oreon.cerebrum.dao.ItemDao;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;

import org.apache.log4j.Logger;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

import javax.jws.WebService;

import org.witchcraft.model.support.Range;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class ItemServiceImplBase extends BaseServiceImpl<Item>
		implements
			ItemService {

	private static final Logger log = Logger
			.getLogger(ItemServiceImplBase.class);

	private ItemDao itemDao;

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	@Override
	public GenericDAO<Item> getDao() {
		return itemDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Item save(Item item) {
		Long id = item.getId();

		itemDao.save(item);

		return item;
	}

	public void delete(Item item) {
		itemDao.delete(item);
	}

	public Item load(Long id) {
		return itemDao.load(id);
	}

	public List<Item> loadAll() {
		return itemDao.loadAll();
	}

	public List<Item> searchByExample(Item item) {
		return itemDao.searchByExample(item);
	}

	public List<Item> searchByExample(Item item, List<Range> rangeObjects) {
		return itemDao.searchByExample(item, rangeObjects);
	}

	/** This method should be overridden by classes that want to filter the load all behavior e.g.
	 * showing 
	 * @return
	 */
	public Item getFilterRecord() {
		return null;
	}

}
