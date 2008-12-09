package com.oreon.kgauge.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.oreon.kgauge.domain.ExamStatus;

@org.springframework.stereotype.Repository
public class ExamDaoImpl extends ExamDaoImplBase {

	private static final Logger log = Logger.getLogger(ExamDaoImpl.class);

	public ExamDaoImpl examDaoImplInstance() {
		return this;
	}
	
	@Override
	public List findActiveExams() {
		String qry = "from Exam e where e.examStatus = ?1 ";
		return executeQuery(qry, new Object[]{ExamStatus.ACTIVE});
	}
	
}
