
/**
 * This is generated code - to edit code or override methods use - FilingOffice class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.domain.dao.impl;

import com.cc.civlit.domain.courtdivisions.FilingOffice;
import com.cc.civlit.domain.dao.FilingOfficeDao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class FilingOfficeDaoImplBase extends BaseDao<FilingOffice>
		implements
			FilingOfficeDao {

	//// FINDERS ///// 
	private static final Logger logger = Logger
			.getLogger(FilingOfficeDaoImplBase.class);

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria, FilingOffice exampleInstance) {

		if (exampleInstance.getLevelOfCourt() != null) {
			criteria = criteria.add(Restrictions.eq("levelOfCourt.id",
					exampleInstance.getLevelOfCourt().getId()));
		}

	}

}
