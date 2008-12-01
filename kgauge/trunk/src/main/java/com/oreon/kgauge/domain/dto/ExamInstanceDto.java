
/**
 * This is generated code - to edit code or override methods use - ExamInstance class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain.dto;

import com.oreon.kgauge.domain.*;
import java.util.Date;

/**
 * This is the result of an exam actually being written by a candidate.
 */

public class ExamInstanceDto {

	private Integer maxScore;

	private Integer candidateScore;

	private CandidateDto candidate;

	private ExamDto exam;

	private java.util.Set<AnsweredQuestionDto> answeredQuestion = new java.util.HashSet<AnsweredQuestionDto>();

	public Integer getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}

	public Integer getCandidateScore() {
		return candidateScore;
	}

	public void setCandidateScore(Integer candidateScore) {
		this.candidateScore = candidateScore;
	}

	public CandidateDto getCandidate() {
		return this.candidate;
	}

	public void setCandidate(CandidateDto candidate) {
		this.candidate = candidate;
	}

	public ExamDto getExam() {
		return this.exam;
	}

	public void setExam(ExamDto exam) {
		this.exam = exam;
	}

	public void addAnsweredQuestion(AnsweredQuestionDto answeredQuestion) {
		//checkMaximumAnsweredQuestion();
		answeredQuestion.setExamInstance(this);
		this.answeredQuestion.add(answeredQuestion);
	}

	public void remove(AnsweredQuestionDto answeredQuestion) {
		this.answeredQuestion.remove(answeredQuestion);
	}

	public java.util.Set<AnsweredQuestionDto> getAnsweredQuestion() {
		return this.answeredQuestion;
	}

	public void setAnsweredQuestion(
			java.util.Set<AnsweredQuestionDto> answeredQuestion) {
		this.answeredQuestion = answeredQuestion;
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	public int getAnsweredQuestionCount() {
		return this.answeredQuestion.size();
	}

	/*
	public void checkMaximumAnsweredQuestion(){
		// if(answeredQuestion.size() > Constants.size())
		// 		throw new BusinessException ("msg.tooMany." + answeredQuestion );
	}*/

}
