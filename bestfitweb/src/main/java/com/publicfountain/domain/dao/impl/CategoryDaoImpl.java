package com.publicfountain.domain.dao.impl;

import java.util.List;

import javax.persistence.*;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

import com.publicfountain.domain.Category;

@org.springframework.stereotype.Repository
public class CategoryDaoImpl extends CategoryDaoImplBase {

	private static final Logger log = Logger.getLogger(CategoryDaoImpl.class);

	public CategoryDaoImpl categoryDaoImplInstance() {
		return this;
	}

	public List<Category> findTopLevelCategories() {
		try {
			String queryStr = "Select c from Category c where c.parent is null";
			Query query = entityManager.createQuery(queryStr);
			//query.setParameter(1, null);
			return query.getResultList();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
