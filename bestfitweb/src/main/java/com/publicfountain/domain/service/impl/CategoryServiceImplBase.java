package com.publicfountain.domain.service.impl;

import com.publicfountain.domain.Category;
import com.publicfountain.domain.service.CategoryService;
import com.publicfountain.domain.dao.CategoryDao;
import java.util.List;
import com.publicfountain.domain.service.CategoryService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;

import org.witchcraft.model.support.errorhandling.BusinessException;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class CategoryServiceImplBase implements CategoryService {

	private static final Logger log = Logger
			.getLogger(CategoryServiceImplBase.class);

	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Category save(Category category) {

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

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
