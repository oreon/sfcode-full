package com.oreon.kgauge.dao.impl;

import org.apache.log4j.Logger;

import com.oreon.kgauge.domain.AnswerChoice;
import com.oreon.kgauge.domain.Question;

@org.springframework.stereotype.Repository
public class QuestionDaoImpl extends QuestionDaoImplBase {

	private static final Logger log = Logger.getLogger(QuestionDaoImpl.class);

	public QuestionDaoImpl questionDaoImplInstance() {
		return this;
	}
	
	@Override
	public AnswerChoice findCorrectChoice(Question question) {
		return executeSingleResultQuery("from AnswerChoice a where a.question.id = ?1 and a.correctChoice = true" ,
				question.getId());
	}
	

}
