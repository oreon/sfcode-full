
/**
 * This is generated code - to edit code or override methods use - GrantedRole class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.dao.impl;

import com.oreon.kgauge.domain.GrantedRole;
import com.oreon.kgauge.dao.GrantedRoleDao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class GrantedRoleDaoImplBase extends BaseDao<GrantedRole>
		implements
			GrantedRoleDao {

	//// FINDERS ///// 
	private static final Logger logger = Logger
			.getLogger(GrantedRoleDaoImplBase.class);

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria, GrantedRole exampleInstance) {

		if (exampleInstance.getUser() != null) {
			criteria = criteria.add(Restrictions.eq("user.id", exampleInstance
					.getUser().getId()));
		}

	}

}
