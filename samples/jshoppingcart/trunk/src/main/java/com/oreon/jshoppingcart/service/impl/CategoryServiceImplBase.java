
/**
 * This is generated code - to edit code or override methods use - Category class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.jshoppingcart.service.impl;

import com.oreon.jshoppingcart.domain.Category;
import com.oreon.jshoppingcart.service.CategoryService;
import com.oreon.jshoppingcart.dao.CategoryDao;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class CategoryServiceImplBase extends BaseServiceImpl<Category>
		implements
			CategoryService {

	private static final Logger log = Logger
			.getLogger(CategoryServiceImplBase.class);

	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public GenericDAO<Category> getDao() {
		return categoryDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Category save(Category category) {
		Long id = category.getId();

		categoryDao.save(category);

		return category;
	}

	public void delete(Category category) {
		categoryDao.delete(category);
	}

	public Category load(Long id) {
		return categoryDao.load(id);
	}

	public List<Category> loadAll() {
		return categoryDao.loadAll();
	}

	public List<Category> searchByExample(Category category) {
		return categoryDao.searchByExample(category);
	}

	/**
	 * For tree view , this method returns top level
	 * elements (whose parent is null )
	 */
	public List<Category> findTopLevelElements() {
		return categoryDao.findTopLevelElements();
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
