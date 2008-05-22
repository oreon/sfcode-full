package com.oreon.kgauge.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import com.oreon.kgauge.domain.Exam;

@org.springframework.stereotype.Repository
public class ExamDaoImpl extends ExamDaoImplBase {

	private static final Logger log = Logger.getLogger(ExamDaoImpl.class);

	public ExamDaoImpl examDaoImplInstance() {
		return this;
	}
	
	
}
