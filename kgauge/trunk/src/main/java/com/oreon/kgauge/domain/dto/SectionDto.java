
/**
 * This is generated code - to edit code or override methods use - Section class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain.dto;

import com.oreon.kgauge.domain.*;
import java.util.Date;

public class SectionDto {

	private String name;

	private ExamDto exam;

	private java.util.Set<QuestionDto> question = new java.util.HashSet<QuestionDto>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExamDto getExam() {
		return this.exam;
	}

	public void setExam(ExamDto exam) {
		this.exam = exam;
	}

	public void addQuestion(QuestionDto question) {
		//checkMaximumQuestion();
		question.setSection(this);
		this.question.add(question);
	}

	public void remove(QuestionDto question) {
		this.question.remove(question);
	}

	public java.util.Set<QuestionDto> getQuestion() {
		return this.question;
	}

	public void setQuestion(java.util.Set<QuestionDto> question) {
		this.question = question;
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	public int getQuestionCount() {
		return this.question.size();
	}

	/*
	public void checkMaximumQuestion(){
		// if(question.size() > Constants.size())
		// 		throw new BusinessException ("msg.tooMany." + question );
	}*/

}
