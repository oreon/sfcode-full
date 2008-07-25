
/**
 * This is generated code - to edit code or override methods use - FirmAdministrator class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.dao.impl;

import com.cc.civlit.domain.FirmAdministrator;
import com.cc.civlit.dao.FirmAdministratorDao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class FirmAdministratorDaoImplBase extends BaseDao<FirmAdministrator>
		implements
			FirmAdministratorDao {

	//// FINDERS ///// 
	private static final Logger logger = Logger
			.getLogger(FirmAdministratorDaoImplBase.class);

	@SuppressWarnings("unchecked")
	public/**
	 * Since email is unique, will try to return a single FirmAdministrator by the
	 * email - if no record is found null will be returned
	 * 
	 */
	FirmAdministrator findByEmail(String email) {

		String qryString = "select c from FirmAdministrator c where c.email = ?1";

		Query query = entityManager.createQuery(qryString).setParameter(1,
				email);
		try {
			return (FirmAdministrator) query.getSingleResult();
		} catch (NoResultException nre) {
			logger.info("No FirmAdministrator found for email: " + email);
			return null;
		}

	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria,
			FirmAdministrator exampleInstance) {

		if (exampleInstance.getFirm() != null) {
			criteria = criteria.add(Restrictions.eq("firm.id", exampleInstance
					.getFirm().getId()));
		}

	}

}
