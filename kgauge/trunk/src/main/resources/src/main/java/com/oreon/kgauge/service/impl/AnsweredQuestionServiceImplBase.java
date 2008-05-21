
/**
 * This is generated code - to edit code or override methods use - AnsweredQuestion class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.service.impl;

import com.oreon.kgauge.domain.AnsweredQuestion;
import com.oreon.kgauge.service.AnsweredQuestionService;
import com.oreon.kgauge.dao.AnsweredQuestionDao;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

import javax.jws.WebService;

import org.witchcraft.model.support.Range;

import com.oreon.kgauge.domain.GrantedRole;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class AnsweredQuestionServiceImplBase
		extends
			BaseServiceImpl<AnsweredQuestion>
		implements
			AnsweredQuestionService {

	private static final Logger log = Logger
			.getLogger(AnsweredQuestionServiceImplBase.class);

	private AnsweredQuestionDao answeredQuestionDao;

	public void setAnsweredQuestionDao(AnsweredQuestionDao answeredQuestionDao) {
		this.answeredQuestionDao = answeredQuestionDao;
	}

	@Override
	public GenericDAO<AnsweredQuestion> getDao() {
		return answeredQuestionDao;
	}

	//// Delegate all crud operations to the Dao ////

	public AnsweredQuestion save(AnsweredQuestion answeredQuestion) {
		Long id = answeredQuestion.getId();

		answeredQuestionDao.save(answeredQuestion);

		return answeredQuestion;
	}

	public void delete(AnsweredQuestion answeredQuestion) {
		answeredQuestionDao.delete(answeredQuestion);
	}

	public AnsweredQuestion load(Long id) {
		return answeredQuestionDao.load(id);
	}

	public List<AnsweredQuestion> loadAll() {
		return answeredQuestionDao.loadAll();
	}

	public List<AnsweredQuestion> searchByExample(
			AnsweredQuestion answeredQuestion) {
		return answeredQuestionDao.searchByExample(answeredQuestion);
	}

	public List<AnsweredQuestion> searchByExample(
			AnsweredQuestion answeredQuestion, List<Range> rangeObjects) {
		return answeredQuestionDao.searchByExample(answeredQuestion,
				rangeObjects);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
