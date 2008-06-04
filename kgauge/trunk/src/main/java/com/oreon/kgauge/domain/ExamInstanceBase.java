
/**
 * This is generated code - to edit code or override methods use - ExamInstance class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain;

import javax.persistence.*;
import java.util.Date;
import org.hibernate.annotations.Cascade;

import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import org.witchcraft.model.jsf.Image;
import java.util.Set;

@MappedSuperclass
@Indexed
//@Analyzer(impl = example.EnglishAnalyzer.class)
/*This is the result of an exam actually being written by a candidate.*/
public abstract class ExamInstanceBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	protected Integer maxScore;

	protected Integer candidateScore;

	/* Default Constructor */
	public ExamInstanceBase() {
	}

	/* Constructor with all attributes */
	public ExamInstanceBase(Integer maxScore, Integer candidateScore) {
		this.maxScore = maxScore;
		this.candidateScore = candidateScore;
	}

	/*
	
	 */
	public Integer getMaxScore() {
		return this.maxScore;
	}

	@Transient
	/*
	
	 */
	public Integer getCandidateScore() {

		candidateScore = 0;
		//sum(answeredQuestion, getQuestion().getScore);

		return this.candidateScore;
	}

	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}

	public void setCandidateScore(Integer candidateScore) {
		this.candidateScore = candidateScore;
	}

	private com.oreon.kgauge.domain.Candidate candidate;

	private com.oreon.kgauge.domain.Exam exam;

	private java.util.Set<com.oreon.kgauge.domain.AnsweredQuestion> answeredQuestion = new java.util.HashSet<com.oreon.kgauge.domain.AnsweredQuestion>();

	@ManyToOne
	@JoinColumn(name = "candidate_ID", nullable = false, updatable = false)
	@XmlTransient
	public com.oreon.kgauge.domain.Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(com.oreon.kgauge.domain.Candidate candidate) {
		this.candidate = candidate;
	}

	@ManyToOne
	@JoinColumn(name = "exam_ID", nullable = false, updatable = true)
	@XmlTransient
	public com.oreon.kgauge.domain.Exam getExam() {
		return this.exam;
	}

	public void setExam(com.oreon.kgauge.domain.Exam exam) {
		this.exam = exam;
	}

	public void add(com.oreon.kgauge.domain.AnsweredQuestion answeredQuestion) {
		answeredQuestion.setExamInstance(examInstanceInstance());
		this.answeredQuestion.add(answeredQuestion);
	}

	public void remove(com.oreon.kgauge.domain.AnsweredQuestion answeredQuestion) {
		this.answeredQuestion.remove(answeredQuestion);
	}

	@OneToMany(mappedBy = "examInstance", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "examInstance_ID", nullable = true)
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
	}

	public abstract ExamInstance examInstanceInstance();

	@Transient
	public String getDisplayName() {
		return maxScore + "";
	}

}
