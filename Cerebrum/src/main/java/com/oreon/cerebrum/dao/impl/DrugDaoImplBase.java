
/**
 * This is generated code - to edit code or override methods use - Drug class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.cerebrum.dao.impl;

import com.oreon.cerebrum.drugs.Drug;
import com.oreon.cerebrum.dao.DrugDao;

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
public class DrugDaoImplBase extends BaseDao<Drug> implements DrugDao {

	//// FINDERS ///// 
	private static final Logger logger = Logger
			.getLogger(DrugDaoImplBase.class);

	@SuppressWarnings("unchecked")
	public/**
	 * Since name is unique, will try to return a single Drug by the
	 * name - if no record is found null will be returned
	 * 
	 */
	Drug findByName(String name) {

		String qryString = "select c from Drug c where c.name = ?1";

		Query query = entityManager.createQuery(qryString)
				.setParameter(1, name);
		try {
			return (Drug) query.getSingleResult();
		} catch (NoResultException nre) {
			logger.info("No Drug found for name: " + name);
			return null;
		}

	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria, Drug exampleInstance) {

	}

}
