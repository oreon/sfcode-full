
/**
 * This is generated code - to edit code or override methods use - User class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.domain.dao.impl;

import com.cc.civlit.domain.auth.User;
import com.cc.civlit.domain.dao.UserDao;

import org.apache.log4j.Logger;
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
	private static final Logger logger = Logger
			.getLogger(UserDaoImplBase.class);

	@SuppressWarnings("unchecked")
	public/**
	 * Since username is unique, will try to return a single User by the
	 * username - if no record is found null will be returned
	 * 
	 */
	User findByUsername(String username) {

		String qryString = "select c from User c where c.username = ?1";

		Query query = entityManager.createQuery(qryString).setParameter(1,
				username);
		try {
			return (User) query.getSingleResult();
		} catch (NoResultException nre) {
			logger.info("No User found for username: " + username);
			return null;
		}

	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria, User exampleInstance) {

	}

}
