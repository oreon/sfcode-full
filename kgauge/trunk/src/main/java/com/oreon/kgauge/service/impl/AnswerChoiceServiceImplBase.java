
/**
 * This is generated code - to edit code or override methods use - AnswerChoice class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.service.impl;

import com.oreon.kgauge.domain.AnswerChoice;
import com.oreon.kgauge.service.AnswerChoiceService;
import com.oreon.kgauge.dao.AnswerChoiceDao;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

import javax.jws.WebService;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class AnswerChoiceServiceImplBase extends BaseServiceImpl<AnswerChoice>
		implements
			AnswerChoiceService {

	private static final Logger log = Logger
			.getLogger(AnswerChoiceServiceImplBase.class);

	private AnswerChoiceDao answerChoiceDao;

	public void setAnswerChoiceDao(AnswerChoiceDao answerChoiceDao) {
		this.answerChoiceDao = answerChoiceDao;
	}

	@Override
	public GenericDAO<AnswerChoice> getDao() {
		return answerChoiceDao;
	}

	//// Delegate all crud operations to the Dao ////

	public AnswerChoice save(AnswerChoice answerChoice) {
		Long id = answerChoice.getId();

		answerChoiceDao.save(answerChoice);

		return answerChoice;
	}

	public void delete(AnswerChoice answerChoice) {
		answerChoiceDao.delete(answerChoice);
	}

	public AnswerChoice load(Long id) {
		return answerChoiceDao.load(id);
	}

	public List<AnswerChoice> loadAll() {
		return answerChoiceDao.loadAll();
	}

	public List<AnswerChoice> searchByExample(AnswerChoice answerChoice) {
		return answerChoiceDao.searchByExample(answerChoice);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
