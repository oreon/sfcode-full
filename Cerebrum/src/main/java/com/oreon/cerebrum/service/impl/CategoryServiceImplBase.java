
/**
 * This is generated code - to edit code or override methods use - Category class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.cerebrum.service.impl;

import com.oreon.cerebrum.drugs.Category;
import com.oreon.cerebrum.service.CategoryService;
import com.oreon.cerebrum.dao.CategoryDao;
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
		checkUniqueConstraints(category);
		categoryDao.save(category);

		return category;
	}

	/** Before saving a record we need to ensure that no unique constraints
	 * will be violated. 
	 * @param customer
	 */
	private void checkUniqueConstraints(Category category) {
		Category existingCategory = null;

		existingCategory = categoryDao.findByCode(category.getCode());
		ensureUnique(category, existingCategory, "Entity.exists.withCode");

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

	public Category findByCode(String code) {
		return categoryDao.findByCode(code);
	}

	public List<Category> searchByExample(Category category) {
		return categoryDao.searchByExample(category);
	}

	public List<Category> searchByExample(Category category,
			List<Range> rangeObjects) {
		return categoryDao.searchByExample(category, rangeObjects);
	}

	/** This method should be overridden by classes that want to filter the load all behavior e.g.
	 * showing 
	 * @return
	 */
	public Category getFilterRecord() {
		return null;
	}

	/**
	 * For tree view , this method returns top level
	 * elements (whose parent is null )
	 */
	public List<Category> findTopLevelElements() {
		return categoryDao.findTopLevelElements();
	}

}
