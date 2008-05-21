
/**
 * This is generated code - to edit code or override methods use - Candidate class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.dao.impl;

import com.oreon.kgauge.domain.Candidate;
import com.oreon.kgauge.dao.CandidateDao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class CandidateDaoImplBase extends BaseDao<Candidate>
		implements
			CandidateDao {

	//// FINDERS ///// 

	@SuppressWarnings("unchecked")
	public/**
	 * Since username is unique, will try to return a single Candidate by the
	 * username - if no record is found null will be returned
	 * 
	 */
	Candidate findByUsername(String username) {

		String qryString = "select c from Candidate c where c.user.username = ?1";

		Query query = entityManager.createQuery(qryString).setParameter(1,
				username);
		try {
			return (Candidate) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public/**
	 * Since email is unique, will try to return a single Candidate by the
	 * email - if no record is found null will be returned
	 * 
	 */
	Candidate findByEmail(String email) {

		String qryString = "select c from Candidate c where c.contactDetails.email = ?1";

		Query query = entityManager.createQuery(qryString).setParameter(1,
				email);
		try {
			return (Candidate) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}

	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria, Candidate exampleInstance) {

	}

}
