
/**
 * This is generated code - to edit code or override methods use - LitigationCase class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.dao.impl;

import com.cc.civlit.domain.LitigationCase;
import com.cc.civlit.dao.LitigationCaseDao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class LitigationCaseDaoImplBase extends BaseDao<LitigationCase>
		implements
			LitigationCaseDao {

	//// FINDERS ///// 
	private static final Logger logger = Logger
			.getLogger(LitigationCaseDaoImplBase.class);

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria,
			LitigationCase exampleInstance) {

		if (exampleInstance.getDivsion() != null) {
			criteria = criteria.add(Restrictions.eq("divsion.id",
					exampleInstance.getDivsion().getId()));
		}

		if (exampleInstance.getFirm() != null) {
			criteria = criteria.add(Restrictions.eq("firm.id", exampleInstance
					.getFirm().getId()));
		}

	}

}
