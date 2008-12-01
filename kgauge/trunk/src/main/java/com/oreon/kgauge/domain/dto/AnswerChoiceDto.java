
/**
 * This is generated code - to edit code or override methods use - AnswerChoice class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain.dto;

import com.oreon.kgauge.domain.*;
import java.util.Date;

public class AnswerChoiceDto {

	private String answerText;

	private Integer score;

	private boolean correctChoice;

	private QuestionDto question;

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public boolean getCorrectChoice() {
		return correctChoice;
	}

	public void setCorrectChoice(boolean correctChoice) {
		this.correctChoice = correctChoice;
	}

	public QuestionDto getQuestion() {
		return this.question;
	}

	public void setQuestion(QuestionDto question) {
		this.question = question;
	}

}
