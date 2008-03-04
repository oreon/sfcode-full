
/**
 * This is generated code - to edit code or override methods use - Question class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.service.BaseServiceImpl;

import com.oreon.kgauge.dao.QuestionDao;
import com.oreon.kgauge.domain.Question;
import com.oreon.kgauge.service.QuestionService;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class QuestionServiceImplBase extends BaseServiceImpl<Question>
		implements
			QuestionService {

	private static final Logger log = Logger
			.getLogger(QuestionServiceImplBase.class);

	private QuestionDao questionDao;

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	@Override
	public GenericDAO<Question> getDao() {
		return questionDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Question save(Question question) {
		Long id = question.getId();

		questionDao.save(question);

		return question;
	}

	public void delete(Question question) {
		questionDao.delete(question);
	}

	public Question load(Long id) {
		return questionDao.load(id);
	}

	public List<Question> loadAll() {
		return questionDao.loadAll();
	}

	public List<Question> searchByExample(Question question) {
		return questionDao.searchByExample(question);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
