
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

	private AnswerChoiceDto answerChoice;

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

	public AnswerChoiceDto getAnswerChoice() {
		return this.answerChoice;
	}

	public void setAnswerChoice(AnswerChoiceDto answerChoice) {
		this.answerChoice = answerChoice;
	}

}
