
/**
 * This is generated code - to edit code or override methods use - Category class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

import com.oreon.kgauge.dao.CategoryDao;
import com.oreon.kgauge.domain.Category;
import com.oreon.kgauge.domain.Exam;

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
