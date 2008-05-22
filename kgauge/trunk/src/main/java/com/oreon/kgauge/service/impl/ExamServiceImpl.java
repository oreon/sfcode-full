package com.oreon.kgauge.service.impl;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oreon.kgauge.domain.Exam;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.oreon.kgauge.service.ExamService", serviceName = "ExamService")
public class ExamServiceImpl extends ExamServiceImplBase {

	private static final Logger log = Logger.getLogger(ExamServiceImpl.class);

	public ExamServiceImpl examServiceImplInstance() {
		return this;
	}

	public EntityManager getEntityManager() {
		return null;
	}
	
	
	
}
