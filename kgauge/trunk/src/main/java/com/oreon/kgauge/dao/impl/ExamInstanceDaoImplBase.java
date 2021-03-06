
/**
 * This is generated code - to edit code or override methods use - ExamInstance class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.dao.impl;

import com.oreon.kgauge.domain.ExamInstance;
import com.oreon.kgauge.dao.ExamInstanceDao;

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
public class ExamInstanceDaoImplBase extends BaseDao<ExamInstance>
		implements
			ExamInstanceDao {

	//// FINDERS ///// 
	private static final Logger logger = Logger
			.getLogger(ExamInstanceDaoImplBase.class);

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria, ExamInstance exampleInstance) {

		if (exampleInstance.getCandidate() != null) {
			criteria = criteria.add(Restrictions.eq("candidate.id",
					exampleInstance.getCandidate().getId()));
		}

		if (exampleInstance.getExam() != null) {
			criteria = criteria.add(Restrictions.eq("exam.id", exampleInstance
					.getExam().getId()));
		}

	}

	public Integer findCandidateScore(ExamInstance examInstance) {

		return executeSingleResultNamedQuery("", new Object[]{examInstance});

	}

	public Integer findMaxScore(ExamInstance examInstance) {

		return executeSingleResultNamedQuery("", new Object[]{examInstance});

	}

}
