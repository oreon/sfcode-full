package com.oreon.kgauge.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.hibernate.search.annotations.Indexed;

import facades.ServiceFacade;

@Entity
@Indexed
@NamedQuery(name="findPopularExams", 
query = "SELECT e FROM Exam e WHERE e.questions > ?1 ORDER  BY e.questions")
public class Exam extends ExamBase implements java.io.Serializable {

	private static final Logger log = Logger.getLogger(Exam.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public Exam() {
	}

	/* Constructor with all attributes */
	public Exam(String examNumber, String name, String description,
			Integer questions, Integer duration, Double price,
			ScoringType scoringStrategy, ExamStatus examStatus, Integer defaultMarksForCorrect) {
		super(examNumber, name, questions, duration, price,
				scoringStrategy, examStatus, duration, defaultMarksForCorrect, description);
	}

	public Exam examInstance() {
		return this;
	}
	
	@Transient
	public List getActiveExams(){
		return ServiceFacade.getInstance().getExamService().findActiveExams();
	}

}
