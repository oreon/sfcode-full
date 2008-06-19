
/**
 * This is generated code - to edit code or override methods use - AnsweredQuestion class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.dao.impl;

import com.oreon.kgauge.domain.AnsweredQuestion;
import com.oreon.kgauge.dao.AnsweredQuestionDao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class AnsweredQuestionDaoImplBase extends BaseDao<AnsweredQuestion>
		implements
			AnsweredQuestionDao {

	//// FINDERS ///// 
	private static final Logger logger = Logger
			.getLogger(AnsweredQuestionDaoImplBase.class);

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria,
			AnsweredQuestion exampleInstance) {

		if (exampleInstance.getQuestion() != null) {
			criteria = criteria.add(Restrictions.eq("question.id",
					exampleInstance.getQuestion().getId()));
		}

		if (exampleInstance.getExamInstance() != null) {
			criteria = criteria.add(Restrictions.eq("examInstance.id",
					exampleInstance.getExamInstance().getId()));
		}

	}

}
