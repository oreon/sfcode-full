
/**
 * This is generated code - to edit code or override methods use - Item class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.cerebrum.dao.impl;

import com.oreon.cerebrum.prescriptions.Item;
import com.oreon.cerebrum.dao.ItemDao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class ItemDaoImplBase extends BaseDao<Item> implements ItemDao {

	//// FINDERS ///// 
	private static final Logger logger = Logger
			.getLogger(ItemDaoImplBase.class);

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria, Item exampleInstance) {

		if (exampleInstance.getPrescription() != null) {
			criteria = criteria.add(Restrictions.eq("prescription.id",
					exampleInstance.getPrescription().getId()));
		}

		if (exampleInstance.getDrug() != null) {
			criteria = criteria.add(Restrictions.eq("drug.id", exampleInstance
					.getDrug().getId()));
		}

	}

}
