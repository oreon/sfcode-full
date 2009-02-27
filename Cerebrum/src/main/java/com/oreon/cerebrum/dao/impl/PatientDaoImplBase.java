
/**
 * This is generated code - to edit code or override methods use - Patient class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.cerebrum.dao.impl;

import com.oreon.cerebrum.prescriptions.Patient;
import com.oreon.cerebrum.dao.PatientDao;

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
public class PatientDaoImplBase extends BaseDao<Patient> implements PatientDao {

	//// FINDERS ///// 
	private static final Logger logger = Logger
			.getLogger(PatientDaoImplBase.class);

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria, Patient exampleInstance) {

	}

}
