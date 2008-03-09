
/**
 * This is generated code - to edit code or override methods use - User class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.dao.impl;

import com.oreon.kgauge.domain.User;
import com.oreon.kgauge.dao.UserDao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class UserDaoImplBase extends BaseDao<User> implements UserDao {

	//// FINDERS ///// 

	@SuppressWarnings("unchecked")
	public/**
	 * Since userName is unique, will try to return a single User by the
	 * userName - if no record is found null will be returned
	 * 
	 */
	User findByUserName(String userName) {

		String qryString = "select c from User c where c.userName = ?1";

		Query query = entityManager.createQuery(qryString).setParameter(1,
				userName);
		try {
			return (User) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}

	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria, User exampleInstance) {

	}

}
