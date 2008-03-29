
/**
 * This is generated code - to edit code or override methods use - GrantedAuthority class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.dao.impl;

import com.oreon.kgauge.domain.GrantedAuthority;
import com.oreon.kgauge.dao.GrantedAuthorityDao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class GrantedAuthorityDaoImplBase extends BaseDao<GrantedAuthority>
		implements
			GrantedAuthorityDao {

	//// FINDERS ///// 

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria,
			GrantedAuthority exampleInstance) {

		if (exampleInstance.getUser() != null) {
			criteria = criteria.add(Restrictions.eq("user.id", exampleInstance
					.getUser().getId()));
		}

	}

}
