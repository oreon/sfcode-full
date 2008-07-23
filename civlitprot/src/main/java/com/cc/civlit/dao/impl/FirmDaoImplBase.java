
/**
 * This is generated code - to edit code or override methods use - Firm class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.dao.impl;

import com.cc.civlit.domain.Firm;
import com.cc.civlit.dao.FirmDao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class FirmDaoImplBase extends BaseDao<Firm> implements FirmDao {

	//// FINDERS ///// 
	private static final Logger logger = Logger
			.getLogger(FirmDaoImplBase.class);

	@SuppressWarnings("unchecked")
	public/**
	 * Since email is unique, will try to return a single Firm by the
	 * email - if no record is found null will be returned
	 * 
	 */
	Firm findByEmail(String email) {

		String qryString = "select c from Firm c where c.contactDetails.email = ?1";

		Query query = entityManager.createQuery(qryString).setParameter(1,
				email);
		try {
			return (Firm) query.getSingleResult();
		} catch (NoResultException nre) {
			logger.info("No Firm found for email: " + email);
			return null;
		}

	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria, Firm exampleInstance) {

	}

}
