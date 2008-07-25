
/**
 * This is generated code - to edit code or override methods use - Role class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.domain.dao.impl;

import com.cc.civlit.domain.auth.Role;
import com.cc.civlit.domain.dao.RoleDao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class RoleDaoImplBase extends BaseDao<Role> implements RoleDao {

	//// FINDERS ///// 
	private static final Logger logger = Logger
			.getLogger(RoleDaoImplBase.class);

	@SuppressWarnings("unchecked")
	public/**
	 * Since name is unique, will try to return a single Role by the
	 * name - if no record is found null will be returned
	 * 
	 */
	Role findByName(String name) {

		String qryString = "select c from Role c where c.name = ?1";

		Query query = entityManager.createQuery(qryString)
				.setParameter(1, name);
		try {
			return (Role) query.getSingleResult();
		} catch (NoResultException nre) {
			logger.info("No Role found for name: " + name);
			return null;
		}

	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria, Role exampleInstance) {

	}

}
