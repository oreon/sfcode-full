package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

@Entity
public class Question extends QuestionBase implements java.io.Serializable {

	private static final Logger log = Logger.getLogger(Question.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public Question() {
	}

	/* Constructor with all attributes */
	public Question(String questionText, DifficultyLevel difficultyLevel) {
		super(questionText, difficultyLevel);
	}

	public Question questionInstance() {
		return this;
	}
	
	@Override
	public AnswerChoice getCorrectChoice() {
		for (AnswerChoice choice : getAnswerChoice()) {
			if(choice.isCorrectChoice())
				return choice;
		}
		
		log.warn("This question has no correct choice");
		return null;
	}

}
