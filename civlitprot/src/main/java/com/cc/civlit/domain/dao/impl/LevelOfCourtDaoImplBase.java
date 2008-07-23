
/**
 * This is generated code - to edit code or override methods use - LevelOfCourt class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.domain.dao.impl;

import com.cc.civlit.domain.courtdivisions.LevelOfCourt;
import com.cc.civlit.domain.dao.LevelOfCourtDao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class LevelOfCourtDaoImplBase extends BaseDao<LevelOfCourt>
		implements
			LevelOfCourtDao {

	//// FINDERS ///// 
	private static final Logger logger = Logger
			.getLogger(LevelOfCourtDaoImplBase.class);

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria, LevelOfCourt exampleInstance) {

		if (exampleInstance.getJurisdiction() != null) {
			criteria = criteria.add(Restrictions.eq("jurisdiction.id",
					exampleInstance.getJurisdiction().getId()));
		}

	}

}
