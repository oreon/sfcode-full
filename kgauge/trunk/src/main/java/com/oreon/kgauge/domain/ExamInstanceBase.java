
/**
 * This is generated code - to edit code or override methods use - ExamInstance class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;

import org.witchcraft.model.jsf.Image;
import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

@MappedSuperclass
/*This is the result of an exam actually being written by a candidate.*/
public abstract class ExamInstanceBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	//named queries : 0

	private static final long serialVersionUID = 1L;

	private com.oreon.kgauge.domain.Candidate candidate;

	private com.oreon.kgauge.domain.Exam exam;

	private java.util.Set<com.oreon.kgauge.domain.AnsweredQuestion> answeredQuestion = new java.util.HashSet<com.oreon.kgauge.domain.AnsweredQuestion>();

	public void setCandidate(com.oreon.kgauge.domain.Candidate candidate) {
		this.candidate = candidate;
	}

	@ManyToOne
	@JoinColumn(name = "candidate_ID", nullable = false)
	public com.oreon.kgauge.domain.Candidate getCandidate() {
		return this.candidate;
	}

	public void setExam(com.oreon.kgauge.domain.Exam exam) {
		this.exam = exam;
	}

	@ManyToOne
	@JoinColumn(name = "exam_ID", nullable = false)
	public com.oreon.kgauge.domain.Exam getExam() {
		return this.exam;
	}

	public void add(com.oreon.kgauge.domain.AnsweredQuestion answeredQuestion) {

		this.answeredQuestion.add(answeredQuestion);
	}

	public void remove(com.oreon.kgauge.domain.AnsweredQuestion answeredQuestion) {
		this.answeredQuestion.remove(answeredQuestion);
	}

	@OneToMany
	@JoinColumn(name = "ExamInstance_ID", nullable = false)
	public java.util.Set<com.oreon.kgauge.domain.AnsweredQuestion> getAnsweredQuestion() {
		return this.answeredQuestion;
	}

	public void setAnsweredQuestion(
			java.util.Set<com.oreon.kgauge.domain.AnsweredQuestion> answeredQuestion) {
		this.answeredQuestion = answeredQuestion;
	}

	@Transient
	public java.util.Iterator<com.oreon.kgauge.domain.AnsweredQuestion> getAnsweredQuestionIterator() {
		return this.answeredQuestion.iterator();
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	@Transient
	public int getAnsweredQuestionCount() {
		return this.answeredQuestion.size();
	}

	public Integer calculateScore() {
		return null;
		//should return Integer
	}

	public abstract ExamInstance examInstanceInstance();

}
