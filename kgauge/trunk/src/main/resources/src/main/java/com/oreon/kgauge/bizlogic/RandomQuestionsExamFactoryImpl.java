package com.oreon.kgauge.bizlogic;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

import com.oreon.kgauge.domain.Exam;
import com.oreon.kgauge.domain.ExamInstance;

import javax.jws.WebService;

public class RandomQuestionsExamFactoryImpl
		extends
			RandomQuestionsExamFactoryImplBase {

	private static final Logger log = Logger
			.getLogger(RandomQuestionsExamFactoryImpl.class);
	
	@Override
	public ExamInstance createExam(Exam exam, Map additionalParameters) {
		// TODO Auto-generated method stub
		super.createExam(exam, additionalParameters);
		
		return new ExamInstance();
	}

}
