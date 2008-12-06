package com.oreon.kgauge.domain;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.apache.log4j.Logger;

import facades.ServiceFacade;

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
	@Transient
	public AnswerChoice getCorrectChoice() {
		//to avoid lazy initialization exception
		 Question question = ServiceFacade.getInstance().getQuestionService().load(getId());
		
		for (AnswerChoice choice : question.getAnswerChoice()) {
			if(choice.isCorrectChoice())
				return choice;
		}
		
		log.warn("This question has no correct choice");
		return null;
	}

}
