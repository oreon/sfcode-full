
/**
 * This is generated code - to edit code or override methods use - AnsweredQuestion class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain.dto;

import com.oreon.kgauge.domain.*;
import java.util.Date;

public class AnsweredQuestionDto {

	private QuestionDto question;

	private ExamInstanceDto examInstance;

	private java.util.Set<AnswerChoiceDto> answerChoice = new java.util.HashSet<AnswerChoiceDto>();

	public QuestionDto getQuestion() {
		return this.question;
	}

	public void setQuestion(QuestionDto question) {
		this.question = question;
	}

	public ExamInstanceDto getExamInstance() {
		return this.examInstance;
	}

	public void setExamInstance(ExamInstanceDto examInstance) {
		this.examInstance = examInstance;
	}

	public void addAnswerChoice(AnswerChoiceDto answerChoice) {
		//checkMaximumAnswerChoice();
		this.answerChoice.add(answerChoice);
	}

	public void remove(AnswerChoiceDto answerChoice) {
		this.answerChoice.remove(answerChoice);
	}

	public java.util.Set<AnswerChoiceDto> getAnswerChoice() {
		return this.answerChoice;
	}

	public void setAnswerChoice(java.util.Set<AnswerChoiceDto> answerChoice) {
		this.answerChoice = answerChoice;
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	public int getAnswerChoiceCount() {
		return this.answerChoice.size();
	}

	/*
	public void checkMaximumAnswerChoice(){
		// if(answerChoice.size() > Constants.size())
		// 		throw new BusinessException ("msg.tooMany." + answerChoice );
	}*/

}
