package com.oreon.kgauge.domain;

import javax.persistence.*;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.search.annotations.Indexed;

import javax.jws.WebService;

import java.util.Date;

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
			ScoringType scoringStrategy, ExamStatus examStatus) {
		super(examNumber, name, description, questions, duration, price,
				scoringStrategy, examStatus);
	}

	public Exam examInstance() {
		return this;
	}

}
