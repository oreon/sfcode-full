
/**
 * This is generated code - to edit code or override methods use - Question class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain.dto;

import com.oreon.kgauge.domain.*;
import java.util.Date;

public class QuestionDto {

	private String questionText;

	private DifficultyLevel difficultyLevel;

	private AnswerChoice correctChoice;

	private java.util.Set<AnswerChoiceDto> answerChoice = new java.util.HashSet<AnswerChoiceDto>();

	private SectionDto section;

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public DifficultyLevel getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public AnswerChoice getCorrectChoice() {
		return correctChoice;
	}

	public void setCorrectChoice(AnswerChoice correctChoice) {
		this.correctChoice = correctChoice;
	}

	public SectionDto getSection() {
		return this.section;
	}

	public void setSection(SectionDto section) {
		this.section = section;
	}

	public void addAnswerChoice(AnswerChoiceDto answerChoice) {
		//checkMaximumAnswerChoice();
		answerChoice.setQuestion(this);
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
