
/**
 * This is generated code - to edit code or override methods use - CaseAdministrator class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.dao.impl;

import com.cc.civlit.domain.CaseAdministrator;
import com.cc.civlit.dao.CaseAdministratorDao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class CaseAdministratorDaoImplBase extends BaseDao<CaseAdministrator>
		implements
			CaseAdministratorDao {

	//// FINDERS ///// 
	private static final Logger logger = Logger
			.getLogger(CaseAdministratorDaoImplBase.class);

	@SuppressWarnings("unchecked")
	public/**
	 * Since email is unique, will try to return a single CaseAdministrator by the
	 * email - if no record is found null will be returned
	 * 
	 */
	CaseAdministrator findByEmail(String email) {

		String qryString = "select c from CaseAdministrator c where c.email = ?1";

		Query query = entityManager.createQuery(qryString).setParameter(1,
				email);
		try {
			return (CaseAdministrator) query.getSingleResult();
		} catch (NoResultException nre) {
			logger.info("No CaseAdministrator found for email: " + email);
			return null;
		}

	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria,
			CaseAdministrator exampleInstance) {

	}

}
