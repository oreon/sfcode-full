
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
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import org.witchcraft.model.jsf.Image;
import java.util.Set;

import java.util.List;
import java.util.ArrayList;

@MappedSuperclass
@Indexed
@Analyzer(impl = org.witchcraft.lucene.analyzers.EnglishAnalyzer.class)
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
	public ExamInstanceBase(Integer maxScore) {

		this.maxScore = maxScore;

	}

	public Integer getMaxScore() {
		return this.maxScore;
	}

	@Transient
	/*
	
	 */
	public Integer getCandidateScore() {

		candidateScore = 0;;

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

	public void addAnsweredQuestion(
			com.oreon.kgauge.domain.AnsweredQuestion answeredQuestion) {
		checkMaximumAnsweredQuestion();
		answeredQuestion.setExamInstance(examInstanceInstance());
		this.answeredQuestion.add(answeredQuestion);
	}

	public void remove(com.oreon.kgauge.domain.AnsweredQuestion answeredQuestion) {
		this.answeredQuestion.remove(answeredQuestion);
	}

	@OneToMany(mappedBy = "examInstance", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@IndexedEmbedded
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

	public void checkMaximumAnsweredQuestion() {
		// if(answeredQuestion.size() > Constants.size())
		// 		throw new BusinessException ("msg.tooMany." + answeredQuestion );
	}

	public Integer calculateScore() {
		return null;
	}

	public abstract ExamInstance examInstanceInstance();

	@Transient
	public String getDisplayName() {
		return exam.getDisplayName() + " " + dateCreated + "";
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public String[] retrieveSearchableFieldsArray() {
		List<String> listSearchableFields = new ArrayList<String>();

		String[] arrFields = new String[listSearchableFields.size()];
		return listSearchableFields.toArray(arrFields);
	}

}
