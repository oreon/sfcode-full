
/**
 * This is generated code - to edit code or override methods use - Category class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.dao.impl;

import com.oreon.kgauge.domain.Category;
import com.oreon.kgauge.dao.CategoryDao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class CategoryDaoImplBase extends BaseDao<Category>
		implements
			CategoryDao {

	//// FINDERS ///// 

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
