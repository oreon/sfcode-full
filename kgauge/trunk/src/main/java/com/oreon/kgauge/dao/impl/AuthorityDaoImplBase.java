
/**
 * This is generated code - to edit code or override methods use - Authority class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.dao.impl;

import com.oreon.kgauge.domain.Authority;
import com.oreon.kgauge.dao.AuthorityDao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class AuthorityDaoImplBase extends BaseDao<Authority>
		implements
			AuthorityDao {

	//// FINDERS ///// 

	@SuppressWarnings("unchecked")
	public/**
	 * Since name is unique, will try to return a single Authority by the
	 * name - if no record is found null will be returned
	 * 
	 */
	Authority findByName(String name) {

		String qryString = "select c from Authority c where c.name = ?1";

		Query query = entityManager.createQuery(qryString)
				.setParameter(1, name);
		try {
			return (Authority) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}

	}

}
