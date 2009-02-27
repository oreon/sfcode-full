
/**
 * This is generated code - to edit code or override methods use - Category class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.cerebrum.dao.impl;

import com.oreon.cerebrum.drugs.Category;
import com.oreon.cerebrum.dao.CategoryDao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class CategoryDaoImplBase extends BaseDao<Category>
		implements
			CategoryDao {

	//// FINDERS ///// 
	private static final Logger logger = Logger
			.getLogger(CategoryDaoImplBase.class);

	@SuppressWarnings("unchecked")
	public/**
	 * Since code is unique, will try to return a single Category by the
	 * code - if no record is found null will be returned
	 * 
	 */
	Category findByCode(String code) {

		String qryString = "select c from Category c where c.code = ?1";

		Query query = entityManager.createQuery(qryString)
				.setParameter(1, code);
		try {
			return (Category) query.getSingleResult();
		} catch (NoResultException nre) {
			logger.info("No Category found for code: " + code);
			return null;
		}

	}

	/**
	 * For tree view , this method returns top level
	 * elements (whose parent is null )
	 */
	public List<Category> findTopLevelElements() {
		String queryStr = "Select c from Category c where c.parent is null";
		Query query = entityManager.createQuery(queryStr);
		return query.getResultList();
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria, Category exampleInstance) {

		if (exampleInstance.getParent() != null) {
			criteria = criteria.add(Restrictions.eq("parent.id",
					exampleInstance.getParent().getId()));
		}

	}

}
