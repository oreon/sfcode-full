
/**
 * This is generated code - to edit code or override methods use - DrugCode class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.cerebrum.dao.impl;

import com.oreon.cerebrum.drugs.DrugCode;
import com.oreon.cerebrum.dao.DrugCodeDao;

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
public class DrugCodeDaoImplBase extends BaseDao<DrugCode>
		implements
			DrugCodeDao {

	//// FINDERS ///// 
	private static final Logger logger = Logger
			.getLogger(DrugCodeDaoImplBase.class);

	@SuppressWarnings("unchecked")
	public/**
	 * Since code is unique, will try to return a single DrugCode by the
	 * code - if no record is found null will be returned
	 * 
	 */
	DrugCode findByCode(String code) {

		String qryString = "select c from DrugCode c where c.code = ?1";

		Query query = entityManager.createQuery(qryString)
				.setParameter(1, code);
		try {
			return (DrugCode) query.getSingleResult();
		} catch (NoResultException nre) {
			logger.info("No DrugCode found for code: " + code);
			return null;
		}

	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria, DrugCode exampleInstance) {

		if (exampleInstance.getDrug() != null) {
			criteria = criteria.add(Restrictions.eq("drug.id", exampleInstance
					.getDrug().getId()));
		}

		if (exampleInstance.getCategory() != null) {
			criteria = criteria.add(Restrictions.eq("category.id",
					exampleInstance.getCategory().getId()));
		}

	}

}
