package com.oreon.kgauge.dao.impl;

import org.apache.log4j.Logger;

import com.oreon.kgauge.domain.ExamInstance;

@org.springframework.stereotype.Repository
public class ExamInstanceDaoImpl extends ExamInstanceDaoImplBase {

	private static final Logger log = Logger
			.getLogger(ExamInstanceDaoImpl.class);

	public ExamInstanceDaoImpl examInstanceDaoImplInstance() {
		return this;
	}
	
	@Override
	public Integer findMaxScore(ExamInstance examInstance) {
		String qry = "select count(questionText) * e.defaultMarksForCorrect from question q, section s, exam e " +
		"where s.exam_id = e.Id  and e.id = ? " +
		" and q.section_id in (select id from section where exam_id = e.id)";
		return executeSingleResultNativeQuery(qry, examInstance);
	}
	
	@Override
	public Integer findCandidateScore(ExamInstance examInstance) {
		String qry = "select count(answerChoice_id) * (select e.defaultMarksForCorrect from exam e where e.id = ei.exam_id) " +
		"from  answeredquestion  aq, examinstance ei where aq.examInstance_id = ei.Id  and ei.id = 59 " +
		" and answerChoice_id in (select id from answerchoice where correctChoice='1')";

		return executeSingleResultNativeQuery(qry, examInstance);
	}
}
