package com.oreon.kgauge.domain;

import javax.persistence.*;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Candidate extends CandidateBase implements java.io.Serializable {

	private static final Logger log = Logger.getLogger(Candidate.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public Candidate() {
	}

	public Candidate candidateInstance() {
		return this;
	}
	
	/**
	 * @return
	 */
	@Transient
	public List<ExamInstance> getExamInstaceAsList(){
		List<ExamInstance> examInstanceList = new ArrayList<ExamInstance>();
		examInstanceList.addAll(getExamInstance());
		return examInstanceList;
	}

}
