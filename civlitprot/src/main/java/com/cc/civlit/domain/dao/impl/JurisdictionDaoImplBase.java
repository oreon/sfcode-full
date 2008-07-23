
/**
 * This is generated code - to edit code or override methods use - Jurisdiction class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.domain.dao.impl;

import com.cc.civlit.domain.courtdivisions.Jurisdiction;
import com.cc.civlit.domain.dao.JurisdictionDao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class JurisdictionDaoImplBase extends BaseDao<Jurisdiction>
		implements
			JurisdictionDao {

	//// FINDERS ///// 
	private static final Logger logger = Logger
			.getLogger(JurisdictionDaoImplBase.class);

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria, Jurisdiction exampleInstance) {

	}

}
